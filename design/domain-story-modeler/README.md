## Domain Story Modeler

A tool to visualize Domain Stories in your browser.

### About

http://domainstorytelling.org

### Run the Modeler

You can try the modeler online at https://www.wps.de/modeler.
To run the modeler locally, download the latest release from [GitHub](https://github.com/WPS/domain-story-modeler/releases).
Extract the zip file and open index.html in your browser.

**Important:** Starting with v1.0.0, the modeler requires that your browser allows third party cookies (How to turn on third party cookies in [Firefox](https://support.mozilla.org/en-US/kb/disable-third-party-cookies) and [Chrome](https://support.google.com/chrome/answer/95647?co=GENIE.Platform%3DDesktop&hl=en)). We rely on storing your modeler's configuration in your browser. That enabled us to develop a cool feature that allows you to choose which icons you want to use for modeling (see below).

### Usage

This is a short guide on how to use the Domain Story Modeler.
We recommend to switch your browser to full screen mode (in most browsers: press F11 key) to increase the available modeling space.

#### Palette

Shows the icons you can choose to record your Domain Story (actors and work objects), a rectangular shape to group icons into logical units, and two useful modeling tools:

- The lasso tool lets you select several icons at once to move them easily.
- the space tool creates or removes space. Click and drag the crosshair to the right or down to create space, or drag to the left or to the top to remove space.

#### Headline

Click on the headline to change the name of the Domain Story and describe it. The description can contain information about assumptions (e.g. that the domain story describes a "happy path" scenario) and variations (e.g. optional activities).
Use ctrl+enter or alt+enter to add line breaks to the description.

#### Context Menu

If you drop an icon from the palette into the canvas you will see a context menu with three sections:

* Top section:
  * wrench icon: change to another the icon
  * bin icon: delete the icon
  * color-palette icon: change the color of the activity/work object
* Middle section:
  * arrow icon: connect two icons with an activity.
  * annotation icon: add comments to an actor or work object
* Bottom section: Instead of dragging icons from the palette, you can can choose the next icon from the context menu and an arrow will connect them automatically.


#### Naming Actors and Work Objects

Double click on an actor's or work object's icon to edit the name. To rename multiple work objects, click the dictionary button ![Dictionary Button](/images/spellcheck.png).

#### Naming and Numbering of Activities

Activities are depicted as arrows. Double click on an arrow to edit the activity's name. If you model an activity between an actor (person, people and system) and a work object (folder, call, email, conversation, information) it will be numbered automatically. By double clicking on the arrow or the number you can manually change the number. All other numbers are adjusted automatically.

If you want to model activities that happen simultaneously, check  the "multiple" checkbox of an activity. This allows you to use the same number multiple times, indicating that these activities happen in parallel. These steps are shown at once in the replay mode.

#### Save and Export Domain Stories

If you want to save your Domain Story, download it as a `.dst` file by clicking the export button ![Export Button](/images/archive.png) or pressing ctrl+s on your keyboard. To continue working on a Domain Story, import a `.dst` file using the upload button ![Upload Button](/images/unarchive.png).
You can also export your diagram as a `.svg` or `.png` file by clicking on the image button ![SVG Button](/images/image.png) and selecting your preferred format.

#### Replay

The replay feature helps you to re-tell a Domain Story sentence by sentence. When you start a replay with the play button ![Play Button](/images/play.png), all activities except the first one disappear. Clicking the forward button ![Forward Button](/images/forward.png) shows the next activity and clicking the previous button ![Previous Button](/images/previous.png) shows the previous one. Editing is disabled in replay mode, but you can zoom (mouse wheel up and down) and scroll (ctrl + mouse wheel up and down). The stop the replay, click the stop button ![Stop Button](/images/stop.png).

#### Keyboard Shortcuts

Click on ![Keyboard Button](/images/keyboard.png) to display all available keyboard shortcuts.

#### Configuring the icon set

We recommend that the icon set is adapted to the domain that you model. You can configure the palette accordingly and share that configuration:

Click on ![Gear Button](/images/gear.png) to open the icon configuration. Configure your icon set by naming it, selecting which icons should be used as actors or as work objects and order these icons (using drag&drop). You can export your configuration as `.domain` file. To switch between configurations, import a different `.domain` file.

If you import a Domain Story from a `.dst` file, your icon configuration will change automatically to the one with which the Domain Story was created - even if you do not have the corresponding `.domain` file.

**Beta feature:** If you want to use icons that are not in the predefined set that comes with the modeler, you can upload your own icons. Different image formats are allowed, but you will achieve best results with SVG because it is scalable (like the icons that come with the modeler). Also, we recommend to use square images because they look better in the pallet and the context pad. If you want a consistent look, consider using the same icon set that we use for the predefined icons: https://material.io/resources/icons/?style=outline

### Examples

The examples' directory contains `.dst` files that you can import to the modeler. They give you an idea how a Domain Story might look like. Use the replay feature to read the story sentence by sentence.
The examples' directory also contains a SVG file for every Domain Story. The images were created using the modeler.

### For Developers

Download the source code or clone the repository.
Please note that we use the main branch for development. The main branch contains the latest features and bug fixes, but they might be undocumented and tested less thoroughly than a release. To be on the safe side, stick with the latest release branch.

Fetch dependencies:

```
npm install
```

Build and open the modeler in your browser:

```
npm run dev
```

Create distributable bundle:

```
npm run all
```

## License

The Domain Story Modeler is licensed under GPLv3.0.
The Modeler uses [bpmn-js](https://github.com/bpmn-io/bpmn-js) which is licensed under the bpmn.io license.

### GPLv3.0

Copyright (c) 2018-2020 WPS - Workplace Solutions GmbH

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.

### bpmn.io License

Copyright (c) 2014-2016 camunda Services GmbH

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

The source code responsible for displaying the bpmn.io logo (two green cogwheels in a box) that links back to http://bpmn.io as part of rendered diagrams MUST NOT be removed or changed. When this software is being used in a website or application, the logo must stay fully visible and not visually overlapped by other elements.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
