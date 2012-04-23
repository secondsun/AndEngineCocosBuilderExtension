package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.util.SAXUtils;
import org.andengine.util.level.EntityLoader;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:25:58 - 18.04.2012
 */
public class CCNodeEntityLoader extends EntityLoader<CCBEntityLoaderDataSource> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float COLOR_COMPONENT_MAX = 255f;

	private static final String ENTITY_NAMES = "CCNode";

	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_X = "x";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_Y = "y";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT = 0;

	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH = "width";
	private static final float TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT = "height";
	private static final float TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT_DEFAULT = 0;

	private static final String TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X = "anchorPointX";
	private static final float TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X_DEFAULT = 0.5f;
	private static final String TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y = "anchorPointY";
	private static final float TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y_DEFAULT = 0.5f;

	private static final String TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET = "ignoreAnchorPointForOffset";
	private static final boolean TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET_DEFAULT = false;

	private static final String TAG_CCNODE_ATTRIBUTE_SCALE_X = "scaleX";
	private static final float TAG_CCNODE_ATTRIBUTE_SCALE_X_DEFAULT = 1;
	private static final String TAG_CCNODE_ATTRIBUTE_SCALE_Y = "scaleY";
	private static final float TAG_CCNODE_ATTRIBUTE_SCALE_Y_DEFAULT = 1;

	private static final String TAG_CCNODE_ATTRIBUTE_ROTATION = "rotation";
	private static final float TAG_CCNODE_ATTRIBUTE_ROTATION_DEFAULT = 0;

	private static final String TAG_CCNODE_ATTRIBUTE_TAG = "tag";
	private static final int TAG_CCNODE_ATTRIBUTE_TAG_DEFAULT = -1; // TODO

	private static final String TAG_CCNODE_ATTRIBUTE_VISIBLE = "visible";
	private static final boolean TAG_CCNODE_ATTRIBUTE_VISIBLE_DEFAULT = true;

	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_RED = "red";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_RED_DEFAULT = 255;
	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_GREEN = "green";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_GREEN_DEFAULT = 255;
	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_BLUE = "blue";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_BLUE_DEFAULT = 255;
	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA = "alpha";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA_DEFAULT = 255;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCNodeEntityLoader() {
		super(CCNodeEntityLoader.ENTITY_NAMES);
	}

	protected CCNodeEntityLoader(final String ... pEntityNames) {
		super(pEntityNames);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Entity onLoadEntity(final String pEntityName, final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final float x = this.getX(pAttributes);
		final float y = this.getY(pAttributes);
		final float width = this.getWidth(pAttributes);
		final float height = this.getHeight(pAttributes);

		final Entity entity = new Entity(x, y, width, height);

		this.setAttributes(entity, pAttributes);

		return entity;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected float getX(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT);
	}

	protected float getY(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT);
	}

	protected float getWidth(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH_DEFAULT);
	}

	protected float getHeight(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT_DEFAULT);
	}

	protected <T extends IEntity> void setAttributes(final T pEntity, final Attributes pAttributes) {
		this.setCCNodeAttributes(pEntity, pAttributes);
	}

	public <T extends IEntity> void setCCNodeAttributes(final T pEntity, final Attributes pAttributes) {
		pEntity.setVisible(this.isVisible(pAttributes));
		pEntity.setColor(this.getRed(pAttributes), this.getGreen(pAttributes), this.getBlue(pAttributes), this.getAlpha(pAttributes));
		pEntity.setRotation(this.getRotation(pAttributes));
		pEntity.setScale(this.getScaleX(pAttributes), this.getScaleY(pAttributes));
		pEntity.setTag(this.getTag(pAttributes));
		pEntity.setAnchorCenter(this.getAnchorPointX(pAttributes), this.getAnchorPointY(pAttributes));

		if(this.isIgnoreAnchorPointForOffset(pAttributes)) {
			pEntity.setOffsetCenter(0, 0);
		}
	}

	private boolean isIgnoreAnchorPointForOffset(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET_DEFAULT);
	}

	protected int getTag(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_TAG, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_TAG_DEFAULT);
	}

	protected float getAnchorPointX(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X_DEFAULT);
	}

	protected float getAnchorPointY(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y_DEFAULT);
	}

	protected float getRotation(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ROTATION, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ROTATION_DEFAULT);
	}

	protected float getScaleX(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_X_DEFAULT);
	}

	protected float getScaleY(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_Y_DEFAULT);
	}

	protected float getRed(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_RED, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_RED_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getGreen(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_GREEN, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_GREEN_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getBlue(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_BLUE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_BLUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getAlpha(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected boolean isVisible(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_VISIBLE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_VISIBLE_DEFAULT);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}