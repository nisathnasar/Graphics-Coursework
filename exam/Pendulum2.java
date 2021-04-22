package exam;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

import GraphicsLab.GraphicsLab;


public class Pendulum2 extends GraphicsLab {
	private float angle;
	private boolean isSwingingRight;
	private boolean isSwingingLeft;
	private boolean cycleThreshold;
	private int count;
	public static void main(String args[]) {
		new Pendulum2().run(WINDOWED, "Pendulum", 0.666f);
	}

	protected void initScene() throws Exception {
		angle = -30;
		isSwingingRight = true;
		isSwingingLeft = false;
		cycleThreshold = false;
		count = 0;
	}

	protected void checkSceneInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			resetAnimations();
		}
	}

	protected void updateScene() {
		if (count < 3) {
			cycleThreshold = false;
		} else {
			cycleThreshold = true;
		}
		if(!cycleThreshold) {
			if (isSwingingRight ) {
				if(angle < 30) {
					angle = angle + 1 * getAnimationScale();
				}
			}
			if (isSwingingLeft) {
				if(angle > -30) {
					angle = angle -	1 * getAnimationScale();
				}
			}
			if (angle >= 30) {
				isSwingingLeft = true;
				isSwingingRight = false;
				count++;
			}
			if (angle <= -30) {
				isSwingingRight = true;
				isSwingingLeft = false;
			}
		} if (cycleThreshold) {
			if(angle != 0) {
				
				
				if(angle < 0) {
					angle = 0;
				} else {
					angle = angle -	1 * getAnimationScale();
				}
				
			}
			if(angle == 0) {
				angle = 0;
			}
		}
	}

	protected void renderScene() {
		GL11.glPushMatrix();
		{
			GL11.glRotatef(angle, 0f, 0, 1f);			
			drawUnitPendulum();
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
