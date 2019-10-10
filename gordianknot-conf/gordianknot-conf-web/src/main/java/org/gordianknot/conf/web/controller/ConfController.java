package org.gordianknot.conf.web.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.gordianknot.conf.client.common.Constant;
import org.gordianknot.conf.client.common.EnvConstants;
import org.gordianknot.conf.client.util.CommonUtil;
import org.gordianknot.conf.web.common.LoginUserInfoUtils;
import org.gordianknot.conf.web.common.ResponseData;
import org.gordianknot.conf.web.domain.Conf;
import org.gordianknot.conf.web.domain.UpdateLog;
import org.gordianknot.conf.web.model.ConfModel;
import org.gordianknot.conf.web.model.NodeInfo;
import org.gordianknot.conf.web.service.ConfService;
import org.gordianknot.conf.web.service.UpdateLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Lists;
/**
* 配置管理控制器
* @author aaron
*/
@Controller
public class ConfController {
    
    @Autowired
    private ConfService confService;
    
    @Autowired
    private UpdateLogService updateLogService;
    
    /**
     * 配置列表頁面
     * @author aaron
     * @param conf
     * @param model
     * @return
     */
    @GetMapping("/")
    public Object index(ConfModel conf, Map<String, Object> model, HttpServletRequest request) {
        List<String> envs = LoginUserInfoUtils.getLoginUserEvns(request);
        if (StringUtils.isBlank(conf.getEnv())) {
            if (envs.contains(EnvConstants.DEV)) {
                conf.setEnv(EnvConstants.DEV);
            } else if (envs.contains(EnvConstants.TEST)) {
                conf.setEnv(EnvConstants.TEST);
            } else if (envs.contains(EnvConstants.ONLINE)) {
                conf.setEnv(EnvConstants.ONLINE);
            } else if (envs.contains(EnvConstants.PROD)) {
                conf.setEnv(EnvConstants.PROD);
            }
        }
        
        if (envs.contains(conf.getEnv())) {
            if (conf.getPage() == 0) conf.setPage(1);
            List<Conf> list = confService.listForPage(conf.getEnv(), conf.getSystemName(),
                    conf.getConfFileName(), conf.getKey(), conf.getPage(), 20);
            List<ConfModel> results = Lists.newArrayList();
            for (Conf c : list) {
                ConfModel cm = new ConfModel();
                BeanUtils.copyProperties(c, cm);
                cm.setNodes(confService.getNodes(c.getEnv(), c.getSystemName(), c.getConfFileName()));
                results.add(cm);
            }
            model.put("confList", results);
            model.put("env", conf.getEnv());
            model.put("conf", conf);
            model.put("msg", "");
        } else {
            model.put("msg", "無操作權限");
            model.put("env", "");
            model.put("conf", new Conf());
        }
        
        return "conf/index";
    }
    
    /**
     * 修改配置
     * @author aaron
     * @param id
     * @param value
     * @param desc
     * @return
     */
    @PostMapping("/conf/update")
    @ResponseBody
    public Object update(@RequestBody List<NodeInfo> nodes, HttpServletRequest request) {
        if (nodes != null) {
            for (NodeInfo node : nodes) {
                Object oldValue;
                Conf conf = confService.get(node.getId());
                oldValue = conf.getValue();
                conf.setValue(node.getValue());
                conf.setModifyDate(new Date());
                confService.save(conf);
                
                //添加修改日誌
                UpdateLog log = UpdateLog.builder().updateObjId(node.getId())
                        .updateTime(new Date())
                        .oldValue(oldValue)
                        .newValue(node.getValue())
                        .username(request.getSession().getAttribute("login_user_name").toString())
                        .updateDesc(node.getDesc()).build();
                updateLogService.save(log);
                // 值是根據推送節點傳來的，只需要修改一次即可，推送就根據節點數量來
                break;
            }
            
            for (NodeInfo node : nodes) {
                Conf conf = confService.get(node.getId());
                //修改zk中的節點的值，告訴客戶端值有修改
                List<String> clients = CommonUtil.getZkClient().getClientServers(conf.getEnv(), conf.getSystemName());
                for (String client : clients) {
                    if (client.split("&")[0].equals(node.getNode()) && client.split("&")[1].equals(conf.getConfFileName())) {
                        CommonUtil.getZkClient().setValue(
                                CommonUtil.buildPath(Constant.ZK_ROOT_PATH, conf.getEnv(),
                                        conf.getSystemName(), client), conf.getValue());
                    }
                }
            }
        }
        
        return ResponseData.ok();
    }
    
    /**
     * 刪除配置
     * @author aaron
     * @param id
     * @return
     */
    @PostMapping("/conf/remove")
    @ResponseBody
    public Object remove(String id) {
        confService.remove(id);
        return "success";
    }
    
    /**
     * 推送配置信息到指定的節點
     * @param nodes
     * @return
     */
    @PostMapping("/conf/push")
    @ResponseBody
    public Object pushConf(@RequestBody List<NodeInfo> nodes) {
        if (nodes != null) {
            for (NodeInfo node : nodes) {
                Conf conf = confService.get(node.getId());
                //修改zk中的節點的值，告訴客戶端值有修改
                List<String> clients = CommonUtil.getZkClient().getClientServers(conf.getEnv(), conf.getSystemName());
                for (String client : clients) {
                    if (client.split("&")[0].equals(node.getNode()) && client.split("&")[1].equals(conf.getConfFileName())) {
                        CommonUtil.getZkClient().setValue(
                                CommonUtil.buildPath(Constant.ZK_ROOT_PATH, conf.getEnv(),
                                        conf.getSystemName(), client), conf.getValue());
                    }
                }
            }
        }
        return ResponseData.ok();
    }
}
