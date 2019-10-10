package org.gordianknot.swagger;
public class SwaggerProperties {
 	
    private boolean enable = true;
    private String group = "api";
    private String title = "Gordianknot API Gateway";
    private String description = "更多技術相關文章請關注：https://github.com/agilego99";
    private String version = "1.0.0";
    private String termsOfServiceUrl = "https://agilego99.blogspot.com";
    private String contact = "agilego99@gmail.com";
    private String license = "Gordianknot";
    private String licenseUrl = "https://agilego99.blogspot.com";
    private String paths = "/error*";
   
    private boolean authHeader = false;
   
    public boolean isAuthHeader() {
 		return authHeader;
 	}
 	public void setAuthHeader(boolean authHeader) {
 		this.authHeader = authHeader;
 	}
 	public boolean isEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }
    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public String getLicenseUrl() {
        return licenseUrl;
    }
    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }
 	public String getPaths() {
 		return paths;
 	}
 	public void setPaths(String paths) {
 		this.paths = paths;
 	}
}
