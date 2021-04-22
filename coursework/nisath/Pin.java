package coursework.nisath;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.sun.tools.javac.util.List;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

public class Pin {
	public static ArrayList<Pin> ALLPINS;
	private int id;
	private float x, y, z;
	private float initX, initY, initZ;
	private float radius = 1f;
	
	public Pin(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		initX = x;
		initY = y;
		initZ = z;
		Pin.ALLPINS.add(this);
		this.id = Pin.ALLPINS.size();
		
	}
	
	
	public float[] translate(float x, float y, float z) {
		boolean collisionFree = true;
		float[] arr = new float[3];
		// compare this pin's coordinates with all others, and disallow translation if it will collide
		for(Pin pin : Pin.ALLPINS) {
			if(pin.getID() != id) {
				if(distanceBetween(this.x , this.z , pin.getX(), pin.getZ()) <= radius*2) {
					collisionFree = false;
					System.out.println("bam " + this.id + " and " + pin.id + " : " + distanceBetween(this.x , this.z , pin.getX(), pin.getZ()));
				}
				
			}
		}
		if(collisionFree) {
			this.x += x;
			this.y += y;
			this.z += z;
			arr[0] = x;
			arr[1] = y;
			arr[2] = z;
			return arr;
		}
		else {
//			arr[0] = 0;
//			arr[1] = 0;
//			arr[2] = 0;
			arr[0] = 0;
			arr[1] = 0;
			arr[2] = z-0.05f;
			return arr;
		}
	}

	public boolean checkIfCollidingWithAnyOtherPin(float x, float y, float z) {
		// compare this pin's coordinates with all others, and disallow translation if it will collide
		for(Pin pin : Pin.ALLPINS) {
			if(pin.getID() != id) {
				if(distanceBetween(this.x + x, this.z + z, pin.getX(), pin.getZ()) <= radius*2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void scale(float height, float width) {
		radius = radius * width;
		GL11.glScalef(width, height, width);
	}
	
	/**
	 * calculates distance between two objects.
	 * 
	 * @param x1 x axis of object 1
	 * @param z1 z axis of object 1
	 * @param x2 x axis of object 2
	 * @param z2 z axis of objext 3
	 * @return
	 */
	private float distanceBetween(float x1, float z1, float x2, float z2) {
		return (float) Math.sqrt((Math.pow(x2, 2) - Math.pow(x1, 2)) + (Math.pow(z2, 2) - Math.pow(z1, 2)));
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public float getRadius() {
		return radius;
	}

	public int getID() {
		return id;
	}

	public void draw() {
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(initX, initY, initZ);
			drawUnitPin();
		}
		GL11.glPopMatrix();
	}
	
	public void drawUnitPin() {
		// base
		Vertex v1 = new Vertex(0f / 2, 0f, -1.10f / 2);
		Vertex v2 = new Vertex(-0.5f / 2, 0f, -1f / 2);
		Vertex v3 = new Vertex(-0.85f / 2, 0f, -0.5f / 2);
		Vertex v4 = new Vertex(-1f / 2, 0f, 0f / 2);
		Vertex v5 = new Vertex(-0.85f / 2, 0f, 0.5f / 2);
		Vertex v6 = new Vertex(-0.5f / 2, 0f, 1f / 2);
		Vertex v7 = new Vertex(0f / 2, 0f, 1.10f / 2);
		Vertex v8 = new Vertex(0.5f / 2, 0f, 1f / 2);
		Vertex v9 = new Vertex(0.85f / 2, 0f, 0.5f / 2);
		Vertex v10 = new Vertex(1f / 2, 0f, 0f / 2);
		Vertex v11 = new Vertex(0.85f / 2, 0f, -0.5f / 2);
		Vertex v12 = new Vertex(0.5f / 2, 0f, -1f / 2);

		// lower
		float h = 1f;
		Vertex v13 = new Vertex(0f, h, -1.10f);
		Vertex v14 = new Vertex(-0.5f, h, -1f);
		Vertex v15 = new Vertex(-0.85f, h, -0.5f);
		Vertex v16 = new Vertex(-1f, h, 0f);
		Vertex v17 = new Vertex(-0.85f, h, 0.5f);
		Vertex v18 = new Vertex(-0.5f, h, 1f);
		Vertex v19 = new Vertex(0f, h, 1.10f);
		Vertex v20 = new Vertex(0.5f, h, 1f);
		Vertex v21 = new Vertex(0.85f, h, 0.5f);
		Vertex v22 = new Vertex(1f, h, 0f);
		Vertex v23 = new Vertex(0.85f, h, -0.5f);
		Vertex v24 = new Vertex(0.5f, h, -1f);

		// mid
		float h2 = 2.2f;
		float dby = 2;
		Vertex v25 = new Vertex(0f / dby, h2, -1.10f / dby);
		Vertex v26 = new Vertex(-0.5f / dby, h2, -1f / dby);
		Vertex v27 = new Vertex(-0.85f / dby, h2, -0.5f / dby);
		Vertex v28 = new Vertex(-1f / dby, h2, 0f / dby);
		Vertex v29 = new Vertex(-0.85f / dby, h2, 0.5f / dby);
		Vertex v30 = new Vertex(-0.5f / dby, h2, 1f / dby);
		Vertex v31 = new Vertex(0f / dby, h2, 1.10f / dby);
		Vertex v32 = new Vertex(0.5f / dby, h2, 1f / dby);
		Vertex v33 = new Vertex(0.85f / dby, h2, 0.5f / dby);
		Vertex v34 = new Vertex(1f / dby, h2, 0f / dby);
		Vertex v35 = new Vertex(0.85f / dby, h2, -0.5f / dby);
		Vertex v36 = new Vertex(0.5f / dby, h2, -1f / dby);

		// upper
		float h3 = 2.7f;
		float dby3 = 1.5f;
		Vertex v37 = new Vertex(0f / dby3, h3, -1.10f / dby3);
		Vertex v38 = new Vertex(-0.5f / dby3, h3, -1f / dby3);
		Vertex v39 = new Vertex(-0.85f / dby3, h3, -0.5f / dby3);
		Vertex v40 = new Vertex(-1f / dby3, h3, 0f / dby3);
		Vertex v41 = new Vertex(-0.85f / dby3, h3, 0.5f / dby3);
		Vertex v42 = new Vertex(-0.5f / dby3, h3, 1f / dby3);
		Vertex v43 = new Vertex(0f / dby3, h3, 1.10f / dby3);
		Vertex v44 = new Vertex(0.5f / dby3, h3, 1f / dby3);
		Vertex v45 = new Vertex(0.85f / dby3, h3, 0.5f / dby3);
		Vertex v46 = new Vertex(1f / dby3, h3, 0f / dby3);
		Vertex v47 = new Vertex(0.85f / dby3, h3, -0.5f / dby3);
		Vertex v48 = new Vertex(0.5f / dby3, h3, -1f / dby3);

		// higher upper
		float h4 = 3f;
		float dby4 = 2.2f;
		Vertex v49 = new Vertex(0f / dby4, h4, -1.10f / dby4);
		Vertex v50 = new Vertex(-0.5f / dby4, h4, -1f / dby4);
		Vertex v51 = new Vertex(-0.85f / dby4, h4, -0.5f / dby4);
		Vertex v52 = new Vertex(-1f / dby4, h4, 0f / dby4);
		Vertex v53 = new Vertex(-0.85f / dby4, h4, 0.5f / dby4);
		Vertex v54 = new Vertex(-0.5f / dby4, h4, 1f / dby4);
		Vertex v55 = new Vertex(0f / dby4, h4, 1.10f / dby4);
		Vertex v56 = new Vertex(0.5f / dby4, h4, 1f / dby4);
		Vertex v57 = new Vertex(0.85f / dby4, h4, 0.5f / dby4);
		Vertex v58 = new Vertex(1f / dby4, h4, 0f / dby4);
		Vertex v59 = new Vertex(0.85f / dby4, h4, -0.5f / dby4);
		Vertex v60 = new Vertex(0.5f / dby4, h4, -1f / dby4);
		Vertex v61 = new Vertex(0, 3.1f, 0);

		// bottom face
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v12.toVector(), v11.toVector(), v10.toVector(), v9.toVector()).submit();

			Colour.WHITE.submit();
			v12.submit();
			v11.submit();
			v10.submit();
			v9.submit();
			v8.submit();
			v7.submit();
			v6.submit();
			v5.submit();
			v4.submit();
			v3.submit();
			v2.submit();
			v1.submit();

			v2.submit();
			v3.submit();
			v4.submit();
			v5.submit();
			v6.submit();
			v7.submit();
			v8.submit();
			v9.submit();
			v10.submit();
			v11.submit();
			v12.submit();
		}
		GL11.glEnd();

		Colour red = new Colour(255, 0, 0);
		Colour green = new Colour(0, 255, 0);
		Colour blue = new Colour(0, 0, 255);
		red = Colour.WHITE;
		green = Colour.WHITE;
		blue = Colour.WHITE;

		// face 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v14.toVector(), v13.toVector(), v1.toVector()).submit();

			red.submit();
			v2.submit();
			v14.submit();
			v13.submit();
			v1.submit();
		}
		GL11.glEnd();
		// face 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v15.toVector(), v14.toVector(), v2.toVector()).submit();

			blue.submit();
			v3.submit();
			v15.submit();
			v14.submit();
			v2.submit();
		}
		GL11.glEnd();
		// face 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v16.toVector(), v15.toVector(), v3.toVector()).submit();

			green.submit();
			v4.submit();
			v16.submit();
			v15.submit();
			v3.submit();
		}
		GL11.glEnd();
		// face 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v17.toVector(), v16.toVector(), v4.toVector()).submit();

			red.submit();
			v5.submit();
			v17.submit();
			v16.submit();
			v4.submit();
		}
		GL11.glEnd();
		// face 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v18.toVector(), v17.toVector(), v5.toVector()).submit();

			blue.submit();
			v6.submit();
			v18.submit();
			v17.submit();
			v5.submit();
		}
		GL11.glEnd();
		// face 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v19.toVector(), v18.toVector(), v6.toVector()).submit();
			green.submit();
			v7.submit();
			v19.submit();
			v18.submit();
			v6.submit();
		}
		GL11.glEnd();
		// face 7
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v20.toVector(), v19.toVector(), v7.toVector()).submit();
			red.submit();
			v8.submit();
			v20.submit();
			v19.submit();
			v7.submit();
		}
		GL11.glEnd();
		// face 8
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v9.toVector(), v21.toVector(), v20.toVector(), v8.toVector()).submit();
			blue.submit();
			v9.submit();
			v21.submit();
			v20.submit();
			v8.submit();
		}
		GL11.glEnd();
		// face 9
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v22.toVector(), v21.toVector(), v9.toVector()).submit();

			green.submit();
			v10.submit();
			v22.submit();
			v21.submit();
			v9.submit();
		}
		GL11.glEnd();
		// face 10
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v23.toVector(), v22.toVector(), v10.toVector()).submit();
			red.submit();
			v11.submit();
			v23.submit();
			v22.submit();
			v10.submit();
		}
		GL11.glEnd();
		// face 11
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v12.toVector(), v24.toVector(), v23.toVector(), v11.toVector()).submit();
			blue.submit();
			v12.submit();
			v24.submit();
			v23.submit();
			v11.submit();
		}
		GL11.glEnd();
		// face 12
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v13.toVector(), v24.toVector(), v12.toVector()).submit();

			green.submit();
			v1.submit();
			v13.submit();
			v24.submit();
			v12.submit();
		}
		GL11.glEnd();

		// ****************************************
		// face 1 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v26.toVector(), v25.toVector(), v13.toVector()).submit();

			red.submit();
			v14.submit();
			v26.submit();
			v25.submit();
			v13.submit();
		}
		GL11.glEnd();
		// face 2 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v15.toVector(), v27.toVector(), v26.toVector(), v14.toVector()).submit();

			blue.submit();
			v15.submit();
			v27.submit();
			v26.submit();
			v14.submit();
		}
		GL11.glEnd();
		// face 3 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v16.toVector(), v28.toVector(), v27.toVector(), v15.toVector()).submit();

			green.submit();
			v16.submit();
			v28.submit();
			v27.submit();
			v15.submit();
		}
		GL11.glEnd();
		// face 4 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v17.toVector(), v29.toVector(), v28.toVector(), v16.toVector()).submit();

			red.submit();
			v17.submit();
			v29.submit();
			v28.submit();
			v16.submit();
		}
		GL11.glEnd();
		// face 5 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v18.toVector(), v30.toVector(), v29.toVector(), v17.toVector()).submit();

			blue.submit();
			v18.submit();
			v30.submit();
			v29.submit();
			v17.submit();
		}
		GL11.glEnd();
		// face 6 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v19.toVector(), v31.toVector(), v30.toVector(), v18.toVector()).submit();

			green.submit();
			v19.submit();
			v31.submit();
			v30.submit();
			v18.submit();
		}
		GL11.glEnd();
		// face 7 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v20.toVector(), v32.toVector(), v31.toVector(), v19.toVector()).submit();

			red.submit();
			v20.submit();
			v32.submit();
			v31.submit();
			v19.submit();
		}
		GL11.glEnd();
		// face 8 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v21.toVector(), v33.toVector(), v32.toVector(), v20.toVector()).submit();

			blue.submit();
			v21.submit();
			v33.submit();
			v32.submit();
			v20.submit();
		}
		GL11.glEnd();
		// face 9 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v22.toVector(), v34.toVector(), v33.toVector(), v21.toVector()).submit();

			green.submit();
			v22.submit();
			v34.submit();
			v33.submit();
			v21.submit();
		}
		GL11.glEnd();
		// face 10 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v23.toVector(), v35.toVector(), v34.toVector(), v22.toVector()).submit();

			red.submit();
			v23.submit();
			v35.submit();
			v34.submit();
			v22.submit();
		}
		GL11.glEnd();
		// face 11 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v24.toVector(), v36.toVector(), v35.toVector(), v23.toVector()).submit();

			blue.submit();
			v24.submit();
			v36.submit();
			v35.submit();
			v23.submit();
		}
		GL11.glEnd();
		// face 12 mid
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v25.toVector(), v36.toVector(), v24.toVector()).submit();

			green.submit();
			v13.submit();
			v25.submit();
			v36.submit();
			v24.submit();
		}
		GL11.glEnd();

		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		GL11.glDisable(GL11.GL_LIGHTING);
		// ****************************************
		// face 1 upper
		red = new Colour(181, 68, 40);
		blue = new Colour(181, 68, 40);
		green = new Colour(181, 68, 40);
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v26.toVector(), v38.toVector(), v37.toVector(), v25.toVector()).submit();

			red.submit();
			v26.submit();
			v38.submit();
			v37.submit();
			v25.submit();
		}
		GL11.glEnd();
		// face 2 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v27.toVector(), v39.toVector(), v38.toVector(), v26.toVector()).submit();

			blue.submit();
			v27.submit();
			v39.submit();
			v38.submit();
			v26.submit();
		}
		GL11.glEnd();
		// face 3 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v28.toVector(), v40.toVector(), v39.toVector(), v27.toVector()).submit();

			green.submit();
			v28.submit();
			v40.submit();
			v39.submit();
			v27.submit();
		}
		GL11.glEnd();
		// face 4 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v29.toVector(), v41.toVector(), v40.toVector(), v28.toVector()).submit();

			red.submit();
			v29.submit();
			v41.submit();
			v40.submit();
			v28.submit();
		}
		GL11.glEnd();
		// face 5 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v30.toVector(), v42.toVector(), v41.toVector(), v29.toVector()).submit();

			blue.submit();
			v30.submit();
			v42.submit();
			v41.submit();
			v29.submit();
		}
		GL11.glEnd();
		// face 6 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v31.toVector(), v43.toVector(), v42.toVector(), v30.toVector()).submit();

			green.submit();
			v31.submit();
			v43.submit();
			v42.submit();
			v30.submit();
		}
		GL11.glEnd();
		// face 7 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v32.toVector(), v44.toVector(), v43.toVector(), v31.toVector()).submit();

			red.submit();
			v32.submit();
			v44.submit();
			v43.submit();
			v31.submit();
		}
		GL11.glEnd();
		// face 8 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v33.toVector(), v45.toVector(), v44.toVector(), v32.toVector()).submit();

			blue.submit();
			v33.submit();
			v45.submit();
			v44.submit();
			v32.submit();
		}
		GL11.glEnd();
		// face 9 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v34.toVector(), v46.toVector(), v45.toVector(), v33.toVector()).submit();

			green.submit();
			v34.submit();
			v46.submit();
			v45.submit();
			v33.submit();
		}
		GL11.glEnd();
		// face 10 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v35.toVector(), v47.toVector(), v46.toVector(), v34.toVector()).submit();

			red.submit();
			v35.submit();
			v47.submit();
			v46.submit();
			v34.submit();
		}
		GL11.glEnd();
		// face 11 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v36.toVector(), v48.toVector(), v47.toVector(), v35.toVector()).submit();

			blue.submit();
			v36.submit();
			v48.submit();
			v47.submit();
			v35.submit();
		}
		GL11.glEnd();
		// face 12 upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v25.toVector(), v37.toVector(), v48.toVector(), v36.toVector()).submit();

			green.submit();
			v25.submit();
			v37.submit();
			v48.submit();
			v36.submit();
		}
		GL11.glEnd();
		GL11.glPopAttrib();
		// ****************************************
		red = new Colour(244, 242, 245);
		blue = new Colour(244, 242, 245);
		green = new Colour(244, 242, 245);

		// face 1 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v38.toVector(), v50.toVector(), v49.toVector(), v37.toVector()).submit();

			red.submit();
			v38.submit();
			v50.submit();
			v49.submit();
			v37.submit();
		}
		GL11.glEnd();
		// face 2 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v39.toVector(), v51.toVector(), v50.toVector(), v38.toVector()).submit();

			blue.submit();
			v39.submit();
			v51.submit();
			v50.submit();
			v38.submit();
		}
		GL11.glEnd();
		// face 3 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v40.toVector(), v52.toVector(), v51.toVector(), v39.toVector()).submit();

			green.submit();
			v40.submit();
			v52.submit();
			v51.submit();
			v39.submit();
		}
		GL11.glEnd();
		// face 4 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v41.toVector(), v53.toVector(), v52.toVector(), v40.toVector()).submit();

			red.submit();
			v41.submit();
			v53.submit();
			v52.submit();
			v40.submit();
		}
		GL11.glEnd();
		// face 5 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v42.toVector(), v54.toVector(), v53.toVector(), v41.toVector()).submit();

			blue.submit();
			v42.submit();
			v54.submit();
			v53.submit();
			v41.submit();
		}
		GL11.glEnd();
		// face 6 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v43.toVector(), v55.toVector(), v54.toVector(), v42.toVector()).submit();

			green.submit();
			v43.submit();
			v55.submit();
			v54.submit();
			v42.submit();
		}
		GL11.glEnd();
		// face 7 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v44.toVector(), v56.toVector(), v55.toVector(), v43.toVector()).submit();

			red.submit();
			v44.submit();
			v56.submit();
			v55.submit();
			v43.submit();
		}
		GL11.glEnd();
		// face 8 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v45.toVector(), v57.toVector(), v56.toVector(), v44.toVector()).submit();

			blue.submit();
			GL11.glTexCoord2f(1.0f, 1.0f);
			v45.submit();
			GL11.glTexCoord2f(1.0f, 0.0f);
			v57.submit();
			GL11.glTexCoord2f(0.0f, 0.0f);
			v56.submit();
			GL11.glTexCoord2f(0.0f, 1.0f);
			v44.submit();
		}
		GL11.glEnd();
		// face 9 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v46.toVector(), v58.toVector(), v57.toVector(), v45.toVector()).submit();
			green.submit();

			GL11.glTexCoord2f(1.0f, 1.0f);
			v46.submit();
			GL11.glTexCoord2f(1.0f, 0.0f);
			v58.submit();
			GL11.glTexCoord2f(0.0f, 0.0f);
			v57.submit();
			GL11.glTexCoord2f(0.0f, 1.0f);
			v45.submit();

		}
		GL11.glEnd();
		// face 10 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v47.toVector(), v59.toVector(), v58.toVector(), v46.toVector()).submit();

			red.submit();
			v47.submit();
			v59.submit();
			v58.submit();
			v46.submit();
		}
		GL11.glEnd();
		// face 11 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v48.toVector(), v60.toVector(), v59.toVector(), v47.toVector()).submit();

			blue.submit();
			v48.submit();
			v60.submit();
			v59.submit();
			v47.submit();
		}
		GL11.glEnd();
		// face 12 higher upper
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v37.toVector(), v49.toVector(), v60.toVector(), v48.toVector()).submit();

			green.submit();
			v37.submit();
			v49.submit();
			v60.submit();
			v48.submit();
		}
		GL11.glEnd();

		// ****************************************
		// face 1 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v50.toVector(), v61.toVector(), v49.toVector()).submit();

			red.submit();
			v50.submit();
			v61.submit();
			v49.submit();
		}
		GL11.glEnd();
		// face 2 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v51.toVector(), v61.toVector(), v50.toVector()).submit();

			blue.submit();
			v51.submit();
			v61.submit();
			v50.submit();
		}
		GL11.glEnd();
		// face 3 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v52.toVector(), v61.toVector(), v51.toVector()).submit();

			green.submit();
			v52.submit();
			v61.submit();
			v51.submit();
		}
		GL11.glEnd();
		// face 4 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v53.toVector(), v61.toVector(), v52.toVector()).submit();

			red.submit();
			v53.submit();
			v61.submit();
			v52.submit();
		}
		GL11.glEnd();
		// face 5 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v54.toVector(), v61.toVector(), v53.toVector()).submit();

			blue.submit();
			v54.submit();
			v61.submit();
			v53.submit();
		}
		GL11.glEnd();
		// face 6 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v55.toVector(), v61.toVector(), v54.toVector()).submit();

			green.submit();
			v55.submit();
			v61.submit();
			v54.submit();
		}
		GL11.glEnd();
		// face 7 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v56.toVector(), v61.toVector(), v55.toVector()).submit();

			red.submit();
			v56.submit();
			v61.submit();
			v55.submit();
		}
		GL11.glEnd();
		// face 8 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v57.toVector(), v61.toVector(), v56.toVector()).submit();

			blue.submit();
			v57.submit();
			v61.submit();
			v56.submit();
		}
		GL11.glEnd();
		// face 9 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v58.toVector(), v61.toVector(), v57.toVector()).submit();

			green.submit();
			v58.submit();
			v61.submit();
			v57.submit();
		}
		GL11.glEnd();
		// face 10 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v59.toVector(), v61.toVector(), v58.toVector()).submit();

			red.submit();
			v59.submit();
			v61.submit();
			v58.submit();
		}
		GL11.glEnd();
		// face 11 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v60.toVector(), v61.toVector(), v59.toVector()).submit();

			blue.submit();
			v60.submit();
			v61.submit();
			v59.submit();
		}
		GL11.glEnd();
		// face 12 top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v49.toVector(), v61.toVector(), v60.toVector()).submit();

			green.submit();
			v49.submit();
			v61.submit();
			v60.submit();
		}
		GL11.glEnd();
	}

	public static void createNewPinSet() {
		Pin.ALLPINS = new ArrayList<>();
	}

}
