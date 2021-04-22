/* Lab6.java
 * A simple scene consisting of a lit house and a textured ground plane
 * 27/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(20,1,20) T(0,-1,-10)] Ground plane
 *  |
 *  +-- [S(20,1,10) Rx(90) T(0,4,-20)] Sky plane
 *  |
 *  +-- [T(4,currentSunMoonY,-19)] Moon
 *  |
 *  +-- [T(2.0,-0.5,-10)] Bee hive
 *  |
 *  +-- [T(0,-1,-12)] Tree
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 *  |
 *  +-- [Ry(35) S(2,2,2) T(-2.5,0,-10)] House
 *      |
 *      +-- [S(1,0.5,1) T(0,0.75,0)] Roof
 */
package exam;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of user input and various types of animation
 * to add a dynamic aspect to a 3D scene
 * 
 * <p>
 * Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis,
 * respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down
 * cursor keys to increase or decrease the viewpoint's distance from the scene
 * origin
 * <li>Press L to lower the sun
 * <li>Press R to raise the sun
 * </ul>
 *
 * <p>
 * Adapted from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class Pendulum extends GraphicsLab {

	private float rotAngle;
	private boolean swingRight;
	private int cycle;
	private int pendulumList;

	public static void main(String args[]) {
		new Pendulum().run(WINDOWED, "Pendulum", 0.66f);
	}

	protected void initScene() {
		rotAngle = -30;
		swingRight = true;
		cycle = 0;
		pendulumList = 1;
		
		GL11.glNewList(pendulumList, GL11.GL_COMPILE);
		{
			drawUnitPendulum();
		}
		GL11.glEndList();
	}

	protected void checkSceneInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			resetAnimations();
		}
	}

	protected void updateScene() {
		if (cycle < 3) {
			//if cycles not complete
			if (swingRight && rotAngle < 30) {		
				//swing one way
				rotAngle+= 1*getAnimationScale();
			}
			if (!swingRight && rotAngle > -30) {	
				//swing other way
				rotAngle-= 1*getAnimationScale();
			}
			if (rotAngle >= 30) {
				swingRight = false;
				//increment cycle
				cycle++;					
			}
			if (rotAngle <= -30) {
				swingRight = true;
			}
		} else {									
			//after cycles if not at rest
			if(rotAngle != 0) {
				//reduce angle
				rotAngle-= 1*getAnimationScale();
				//if gone past rest
				if(rotAngle < 0) {
					//set angle to 0
					rotAngle = 0;
				}
			}
		}
	}

	protected void renderScene() {
		GL11.glPushMatrix();
		{
			GL11.glRotatef(rotAngle, 0f, 0, 1f);
			GL11.glCallList(pendulumList);
		}
		GL11.glPopMatrix();

	}
	
	
	
	

	// --------------------------------------render-methods------------------------------------------

	// --------------------------------------super-methods--------------------------------------------

	protected void cleanupScene() {
	}

	private void resetAnimations() {

	}

	// --------------------------------------mesh-methods---------------------------------------------

	private void drawUnitPendulum() {
		GL11.glPushMatrix();
		{
			

			GL11.glTranslatef(0.0f, -5, -15.0f);

			GL11.glPushMatrix();
			{
				GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
				new Cylinder().draw(0.1f, 0.1f, 5f, 10, 10);
			}
			GL11.glPopMatrix();

			new Sphere().draw(0.5f, 50, 50);
		}
		GL11.glPopMatrix();
	}

}
