package Designer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * The shape designer is a utility class which assits you with the design of 
 * a new 3D object. Replace the content of the drawUnitShape() method with
 * your own code to creates vertices and draw the faces of your object.
 * 
 * You can use the following keys to change the view:
 *   - TAB		switch between vertex, wireframe and full polygon modes
 *   - UP		move the shape away from the viewer
 *   - DOWN     move the shape closer to the viewer
 *   - X        rotate the camera around the x-axis (clockwise)
 *   - Y or C   rotate the camera around the y-axis (clockwise)
 *   - Z        rotate the camera around the z-axis (clockwise)
 *   - SHIFT    keep pressed when rotating to spin anti-clockwise
 *   - A 		Toggle colour (only if using submitNextColour() to specify colour)
 *   - SPACE	reset the view to its initial settings
 *  
 * @author Remi Barillec
 *
 */
public class ShapeDesigner extends AbstractDesigner {
	
	/** Main method **/
	public static void main(String args[])
    {   
		new ShapeDesigner().run( WINDOWED, "Designer", 0.01f);
    }
	
	/** Draw the shape **/
    protected void drawUnitShape()
    {
    	drawUnitCube();
    	drawUnitPoint();
    }
    
	/*/** Draw the shape **/
    protected void drawUnitCube(){
    	
    	 // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(2.0f, 0.5f,  0.5f);
        Vertex v2 = new Vertex(2.0f, 0.5f, -0.5f);
        Vertex v3 = new Vertex(-2.0f, 0.5f, -0.5f);
        Vertex v4 = new Vertex(-2.0f, 0.5f, 0.5f);
        Vertex v5 = new Vertex(2.0f, -0.5f, 0.5f);
        Vertex v6 = new Vertex(2.0f, -0.5f, -0.5f);
        Vertex v7 = new Vertex(-2.0f, -0.5f, -0.5f);
        Vertex v8 = new Vertex(-2.0f, -0.5f, 0.5f);

        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v3.toVector(),v2.toVector(),v1.toVector(),v4.toVector()).submit();
            Colour.RED.submit();
            
            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
            Colour.RED.submit();
            
            v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face: keep this one the same
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
            Colour.RED.submit();
            
            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
            Colour.RED.submit();
            
            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
            Colour.RED.submit();
            
            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
            Colour.RED.submit();
            
            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
    
    private void drawUnitPoint()
    {
    	Vertex v3 = new Vertex(-2.0f, 0.5f, -0.5f);//top left
        Vertex v4 = new Vertex(-2.0f, 0.5f, 0.5f);//top right
        Vertex v7 = new Vertex(-2.0f, -0.5f, -0.5f);//bottom left
        Vertex v8 = new Vertex(-2.0f, -0.5f, 0.5f);//bottom right
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            Colour.RED.submit();
            
            v8.submit();
            v4.submit();
            v3.submit();
            v7.submit();
        }
        GL11.glEnd();
        
        float extrusion = 1f;
        
        Vertex v11 = new Vertex(-2.0f - extrusion, -0.5f, -0.5f);//left
        Vertex v12 = new Vertex(-2.0f -extrusion, -0.5f, 0.5f);//right
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            Colour.RED.submit();
            
            v12.submit();
            v4.submit();
            v3.submit();
            v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            Colour.RED.submit();
            
            v7.submit();            
            v8.submit();
            v12.submit();
            v11.submit();
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            Colour.RED.submit();
            v8.submit();
            v4.submit();
            v12.submit();
            
        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            Colour.RED.submit();
            v3.submit();
            v7.submit();
            v11.submit();
            
        }
        GL11.glEnd();
    }
}
