'use strict';

import { ACTIVITY, TEXTANNOTATION, ACTOR, WORKOBJECT } from '../../language/elementTypes';
import { getAllCanvasObjects, getAllGroups } from '../../language/canvasElementRegistry';
import { createConfigFromDictionaries } from '../iconSetCustomization/persitence';
import { removeDirtyFlag } from './dirtyFlag';
import { getTypeDictionary } from '../../language/icon/dictionaries';
import { sanitizeForDesktop } from '../../util/Sanitizer';
import { getIconset } from '../../language/icon/iconConfig';

let infoText = document.getElementById('infoText');

export function downloadDST(filename, text) {

  let configJSONString = {};
  const iconConfig = getIconset();
  let actors = iconConfig.actors;
  let workObjects = iconConfig.workObjects;

  if (!actors.size>0) {
    actors = getTypeDictionary(ACTOR);
  }
  if (!workObjects.size>0) {
    workObjects = getTypeDictionary(WORKOBJECT);
  }

  configJSONString = JSON.stringify(createConfigFromDictionaries(actors, null, workObjects, null, document.getElementById('currentDomainName').innerText));
  let configAndDST = {
    domain: configJSONString,
    dst: text
  };
  let json =JSON.stringify(configAndDST);

  filename = sanitizeForDesktop(filename);
  let element = document.createElement('a');
  element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(json));
  element.setAttribute('download', filename + '.dst');

  element.style.display = 'none';
  document.body.appendChild(element);

  element.click();

  removeDirtyFlag();

  document.body.removeChild(element);
}

export function createObjectListForDSTDownload(version) {
  let allObjectsFromCanvas = getAllCanvasObjects();
  let groups = getAllGroups();

  let objectList = [];

  allObjectsFromCanvas.forEach(canvasElement =>{
    if (canvasElement.type == ACTIVITY) {
      objectList.push(canvasElement.businessObject);
    }

    // ensure that Activities are always after Actors, Workobjects and Groups in .dst files
    else {
      if (canvasElement.type == TEXTANNOTATION) {
        canvasElement.businessObject.width = canvasElement.width;
        canvasElement.businessObject.height = canvasElement.height;
      }
      objectList.unshift(canvasElement.businessObject);
    }
  });

  groups.forEach(group => {
    objectList.push(group.businessObject);
  });

  let text = '';
  if (infoText) {
    text = infoText.innerText ;
  }

  objectList.push({ info: text });
  objectList.push({ version: version });
  return objectList;
}
