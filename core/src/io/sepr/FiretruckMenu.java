package io.sepr;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.Align;

public class FiretruckMenu {

	public static Stage stage;
	private static Table backgroundTable, iconTable, barTableTruck1, barTableTruck2;
	static ArrayList<ProgressBar> progressBars = new ArrayList<ProgressBar>();

	// Creates an image button from a texture by defining a drawable texture region.
	private static ImageButton createImageButton(Texture tex) {
		return new ImageButton(new TextureRegionDrawable(new TextureRegion(tex)));
	}

	private static ProgressBar.ProgressBarStyle progressBarStyleHP() {
		// set the colour red for the background
		Pixmap pixmapHP = new Pixmap(60, 5, Format.RGBA8888);
		pixmapHP.setColor(Color.RED);
		pixmapHP.fill();
		TextureRegionDrawable drawableHP = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapHP)));
		pixmapHP.dispose();
		ProgressBar.ProgressBarStyle progressBarStyleHP = new ProgressBar.ProgressBarStyle();
		progressBarStyleHP.background = drawableHP;

		// sets drawable with width=0 to get rid of knob
		pixmapHP = new Pixmap(0, 5, Format.RGBA8888);
		pixmapHP.setColor(Color.GREEN);
		pixmapHP.fill();
		TextureRegionDrawable drawableHP2 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapHP)));
		pixmapHP.dispose();
		progressBarStyleHP.knob = drawableHP2;

		// before the knob is represented by the drawable with the width of the
		// background
		Pixmap pixmapHP2 = new Pixmap(60, 5, Format.RGBA8888);
		pixmapHP2.setColor(Color.GREEN);
		pixmapHP2.fill();
		drawableHP = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapHP2)));
		pixmapHP2.dispose();
		progressBarStyleHP.knobBefore = drawableHP;

		return progressBarStyleHP;

	}

	private static ProgressBar.ProgressBarStyle progressBarStyleWater() {
		// set the colour white for the background
		Pixmap pixmapWater = new Pixmap(60, 5, Format.RGBA8888);
		pixmapWater.setColor(Color.WHITE);
		pixmapWater.fill();
		TextureRegionDrawable drawableWater = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapWater)));
		pixmapWater.dispose();
		ProgressBar.ProgressBarStyle progressBarStyleWater = new ProgressBar.ProgressBarStyle();
		progressBarStyleWater.background = drawableWater;

		// sets drawable with width=0 to get rid of knob
		pixmapWater = new Pixmap(0, 5, Format.RGBA8888);
		pixmapWater.setColor(Color.BLUE);
		pixmapWater.fill();
		TextureRegionDrawable drawableWater2 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapWater)));
		pixmapWater.dispose();
		progressBarStyleWater.knob = drawableWater2;

		// before the knob is represented by the drawable with the width of the
		// background
		Pixmap pixmapWater2 = new Pixmap(60, 5, Format.RGBA8888);
		pixmapWater2.setColor(Color.BLUE);
		pixmapWater2.fill();
		drawableWater = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapWater2)));
		pixmapWater2.dispose();
		progressBarStyleWater.knobBefore = drawableWater;

		return progressBarStyleWater;
	}

	/*
	 * Initialises the stage, backgroundTable, iconTable, barTableTruck1 and
	 * barTableTruck2. Creates four image buttons UIBackground, firetruckUI1,
	 * firetruckUI2 and firetruckUI3. Creates four bars, waterBar, waterBar2,
	 * healthBar and healthBar2. Add actors to their respective tables. Then add the
	 * tables to the stage.
	 */
	public static void create() {

		stage = new Stage();

		backgroundTable = new Table();
		backgroundTable.setWidth(stage.getWidth());
		// things added to the table are added to the left and at the top
		backgroundTable.align(Align.left | Align.top);
		backgroundTable.setPosition(0, (float) (Gdx.graphics.getHeight() / 2.5));

		iconTable = new Table();
		iconTable.setWidth(stage.getWidth());
		// things added to the table are added to the left and at the top
		iconTable.align(Align.left | Align.top);
		iconTable.setPosition(0, (float) (Gdx.graphics.getHeight() / 3));

		barTableTruck1 = new Table();
		barTableTruck1.setWidth(10f);
		barTableTruck1.align(Align.left | Align.top);
		barTableTruck1.setPosition(10, (float) (Gdx.graphics.getHeight() / 3.2));

		barTableTruck2 = new Table();
		barTableTruck2.setWidth(10f);
		barTableTruck2.align(Align.center | Align.left);
		barTableTruck2.setPosition(10, (float) (Gdx.graphics.getHeight() / 4.8));

		ImageButton UIBackground = createImageButton(new Texture("stationUI.png"));
		ImageButton firetruckUI1 = createImageButton(new Texture("truckUI.png"));
		ImageButton firetruckUI2 = createImageButton(new Texture("truckUI.png"));
		// ImageButton firetruckUI3 = createImageButton(new Texture("truckUI.png"));

		// updated using a SetValue() between 0 and 1
		addProgressBar(new ProgressBar(0.0f, 80.0f, 1.0f, false, progressBarStyleWater()), MainGame.truck1.getWater());
		addProgressBar(new ProgressBar(0.0f, 100.0f, 1.0f, false, progressBarStyleHP()), MainGame.truck1.getHealth());
		addProgressBar(new ProgressBar(0.0f, 200.0f, 1.0f, false, progressBarStyleWater()), MainGame.truck2.getWater());
		addProgressBar(new ProgressBar(0.0f, 50.0f, 1.0f, false, progressBarStyleHP()), MainGame.truck2.getHealth());

		backgroundTable.add(UIBackground).padLeft(20);

		iconTable.row().height(40).padLeft(30);
		iconTable.add(firetruckUI1).width(Value.percentWidth(0.025F, iconTable));
		iconTable.row().height(100).padLeft(30);
		iconTable.add(firetruckUI2);
		// iconTable.row().height(50).padLeft(30);
		// iconTable.add(firetruckUI3);

		barTableTruck1.row().padLeft(13);
		barTableTruck1.add(progressBars.get(1)).maxWidth(60);
		barTableTruck1.row().padLeft(13);
		barTableTruck1.add(progressBars.get(0)).maxWidth(60);

		barTableTruck2.row().padLeft(13);
		barTableTruck2.add(progressBars.get(3)).maxWidth(60);
		barTableTruck2.row().padLeft(13);
		barTableTruck2.add(progressBars.get(2)).maxWidth(60);

		stage.addActor(backgroundTable);
		stage.addActor(iconTable);
		stage.addActor(barTableTruck1);
		stage.addActor(barTableTruck2);
		Gdx.input.setInputProcessor(stage);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	private static void addProgressBar(ProgressBar bar, float value) {
		bar.setValue(value);
		bar.setAnimateDuration(0.25f);
		bar.setBounds(6, 6, 60, 12);
		progressBars.add(bar);
	}

	public static void update(float delta) {
		if (!progressBars.isEmpty()) {
			progressBars.get(0).setValue(MainGame.truck1.getWater());
			progressBars.get(1).setValue(MainGame.truck1.getHealth());
			progressBars.get(2).setValue(MainGame.truck2.getWater());
			progressBars.get(3).setValue(MainGame.truck2.getHealth());
		}
		stage.act(delta);
		stage.draw();
	}

}
