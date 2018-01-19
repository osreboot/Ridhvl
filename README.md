**Reboot's In-Depth High Velocity Library**

<p align="center">
  <img src="https://www.dropbox.com/s/dwnrte0b9bpzqf5/Icon.png?raw=1" alt="Ridhvl Logo"/>
</p>

Ridhvl is a library designed to provide quick shortcuts and templates for game jams such as the *Ludum Dare* and *MiniLD*.

Admittedly, this library was designed with my own personal use in mind. However, pull requests that provide
*helpful* and *logical* additions will be accepted.

Ridhvl requires [Lwjgl 2](http://legacy.lwjgl.org/) and [slick-util](http://slick.ninjacave.com/) to function.

---

**Minimum Viable Product**

```java
package com.osreboot.ridhvltest;

//Don't forget to statically import painter!
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

/*
 * There are a number of templates to choose from - HvlTemplateInteg2D is the simplest
 * because it already includes content loaders (you don't have to make your own).
 */
public class Main extends HvlTemplateInteg2D{

  public static void main(String args[]){
    new Main();//instantiate our program
  }

	public Main() {
    //Window configuration
    //super(max_framerate, window_width, window_height, window_title, window_display_mode)
		super(60, 1280, 720, "Ridhvl Test!", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {//called once on program startup
    // Loading a texture
    // - ".png" is implied
    // - HvlTextureLoader and HvlSoundLoader both default to looking in the "/res" folder of your project
		getTextureLoader().loadResource("Icon");
	}

	@Override
	public void update(float delta) {//called every frame - "delta" is the time (in seconds) since the last frame update
    //Drawing a quad
    //hvlDrawQuad(quad_origin_x, quad_origin_y, quad_width, quad_height, texture_or_color)
    //The texture is referenced with the order in which it was loaded (0 for first, 1 for second, etc.)
    //Also look into hvlDrawQuadc - draws with the origin at the center of the quad rather than top-left
		hvlDrawQuad(100, 100, 512, 512, getTexture(0));
	}

}
```
