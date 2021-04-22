package exam;
import GraphicsLab.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import java.awt.*;
import javax.swing.*;
import java. lang. Math.*;

public class PartA extends GraphicsLab{
	
	private float count = 0;
	
	//Hook circle x value.
	private float AX = 0.0f;
	//Hook circle y value.
	private float AY = 5.0f;
	//Hook circle radius value.
	private float AR = 0.5f;
	//Pendulum circle x value.
	private float BX = 0.0f;
	//Pendulum circle y value.
	private float BY = -5.0f;
	//Pendulum circle radius value.
	private float BR = 1.0f;
	//Angle of pendulum.
	private float angle = -30f;
	//Does the Pendulum Swing?
	private boolean pendulumSwing = true;

	public static void main(String args[]) {
		
	    	new PartA().run(WINDOWED,"Pendulum Swing",0.01f);    
	    	
	}

	@Override
	protected void initScene() throws Exception {
		
		//Global ambient light level.
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        //Set the global ambient lighting.
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));

        //First light for the scene is white.
        float diffuse0[]  = { 0.6f,  0.6f, 0.6f, 1.0f};
        //Dim ambient contribution.
        float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        //Positioned above the viewpoint.
        float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f}; 

        //Supply OpenGL with the properties for the first light.
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        //Enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        //Enable lighting calculations.
        GL11.glEnable(GL11.GL_LIGHTING);
        //Ensure that all normals are re-normalised after transformations automatically.
        GL11.glEnable(GL11.GL_NORMALIZE);       
		
	}

	@Override
	protected void checkSceneInput() {
    	
    }

	@Override
	protected void updateScene() {
	
		if (count  <= 3) {
			
			if(angle >= 30.0f) {
				pendulumSwing = false;
				count++;
			} else if (angle <= -30.0) {
				pendulumSwing = true;
			}
			
			if(pendulumSwing) {
				angle = angle + 0.5f;
			} else {
				angle = angle - 0.5f;
			}
			
		}
		
		if (count == 3) {
			pendulumSwing = false;
			angle = 0;
		}
	
	}

	@Override
	protected void renderScene() {
		
		//Enables the colour to show after lighting is used.
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glColor3f( 1, 0, 0);
		
		GL11.glPushMatrix(); {
		GL11.glTranslatef(AX,AY,0.0f);
		drawHook(Colour.WHITE, AR);
		}
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix(); {
	    GL11.glRotatef(angle,0.0f,0.0f,1.0f);
        GL11.glTranslatef(BX,BY,0.0f);
	    drawPendulum(Colour.BLUE, BR);
		}
		GL11.glPopMatrix();
	}
	
	 protected void setSceneCamera() {
	    	
	        //Default projection settings.
	    	super.setSceneCamera();
	        
	        //Position the camera up and back.
	        GLU.gluLookAt(0.0f,3.0f,20.0f, 0.0f,0.0f,0.0f, 0.0f,1.0f,0.0f);
	   }

	 protected void cleanupScene() {}
	
	 private void drawPendulum(Colour colour, float size) {
		 
	    	//Draw a coloured sphere.
	    	colour.submit();
	     		     	
	     	Sphere pendulum = new Sphere();
	     	pendulum.draw(size, 50, 50);
	 }
	 
	 private void drawHook(Colour colour, float size) {
	    	
	    	//Draw a coloured sphere.
	    	colour.submit();
	     		     	
	     	Sphere ball = new Sphere();
	     	ball.draw(size, 100, 100);
	 }


}
