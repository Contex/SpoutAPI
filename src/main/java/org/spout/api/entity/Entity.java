/*
 * This file is part of SpoutAPI (http://www.spout.org/).
 *
 * SpoutAPI is licensed under the SpoutDev License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.api.entity;

import org.spout.api.Source;
import org.spout.api.collision.model.CollisionModel;
import org.spout.api.datatable.Datatable;
import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.geo.cuboid.Region;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Pointm;
import org.spout.api.inventory.Inventory;
import org.spout.api.math.Vector3;
import org.spout.api.math.Vector3m;
import org.spout.api.model.Model;
import org.spout.api.util.thread.DelayedWrite;
import org.spout.api.util.thread.LiveRead;
import org.spout.api.util.thread.SnapshotRead;

/**
 * Represents an entity, which may or may not be spawned into the world.
 */
public interface Entity extends Datatable, Source {
	public int getId();

	/**
	 * Gets the controller for the entity
	 *
	 * @return the controller
	 */
	@SnapshotRead
	public Controller getController();

	/**
	 * Sets the controller for the entity
	 *
	 * @param controller
	 */
	@DelayedWrite
	public void setController(Controller controller);

	// TODO - add thread timing annotations
	public void setModel(Model model);

	public Model getModel();

	public void setCollision(CollisionModel model);

	public CollisionModel getCollision();
	
	/**
	 * Gets the x coordinate this entity is located at
	 * 
	 * @return x coordinate
	 */
	public float getX();
	
	/**
	 * Gets the y coordinate this entity is located at
	 * 
	 * @return y coordinate
	 */
	public float getY();
	
	/**
	 * Gets the z coordinate this entity is located at
	 * 
	 * @return z coordinate
	 */
	public float getZ();
	
	/**
	 * Returns a copy of a point with the position that this entity is located at.
	 * 
	 * Modifications to this position will not be reflected without calling {@link #setPosition(Point)}.
	 * 
	 * @return position
	 */
	public Pointm getPosition();
	
	/**
	 * Returns a copy of the velocity of this entity. 
	 * 
	 * Modifications to this vector will not be reflected without calling {@link #setVelocity(Vector3)}.
	 * 
	 * @return velocity vector
	 */
	public Vector3m getVelocity();
	
	/**
	 * Sets the position of this entity. Will teleport it to the new position.
	 * 
	 * @param p
	 */
	public void setPosition(Point p);

	/**
	 * Sets the velocity of this entity.
	 * 
	 * @param v
	 */
	public void setVelocity(Vector3 v);
	
	/**
	 * Gets the yaw of this entity.
	 * 
	 * @return yaw
	 */
	public float getYaw();
	
	/**
	 * Sets the yaw of this entity.
	 * 
	 * @param yaw
	 */
	public void setYaw(float yaw);
	
	/**
	 * Gets the pitch of this entity.
	 * 
	 * @return pitch
	 */
	public float getPitch();
	
	/**
	 * Sets the pitch of this entity
	 * 
	 * @param pitch
	 */
	public void setPitch(float pitch);
	
	/**
	 * Called when the entity is set to be sent to clients
	 */
	public void onSync();

	/**
	 * Returns true if this entity's controller is the provided controller
	 *
	 * @param clazz
	 * @return true if this entity's controller is the provided controller
	 */
	public boolean is(Class<? extends Controller> clazz);

	/**
	 * Returns true if this entity is spawned and being Simulated in the world
	 *
	 * @return spawned
	 */
	public boolean isSpawned();

	/**
	 * Gets the chunk the entity resides in, or null if unspawned.
	 *
	 * @return chunk
	 */
	@SnapshotRead
	public Chunk getChunk();

	/**
	 * Gets the region the entity is associated and managed with, or null if
	 * unspawned.
	 *
	 * @return region
	 */
	@SnapshotRead
	public Region getRegion();

	/**
	 * Gets the world the entity is associated with, or null if unspawned.
	 *
	 * @return world
	 */
	@SnapshotRead
	public World getWorld();

	/**
	 * Called just before a snapshot update.
	 */
	public void finalizeRun();

	/**
	 * Kills the entity. This takes effect at the next snapshot.
	 *
	 * If the entity's position is set before the next snapshot, the entity
	 * won't be removed.
	 *
	 * @return true if the entity was alive
	 */
	@DelayedWrite
	@LiveRead
	public boolean kill();

	/**
	 * True if the entity is dead.
	 *
	 * @return dead
	 */
	@SnapshotRead
	public boolean isDead();

	/**
	 * True if the entity is dead.
	 *
	 * @return dead
	 */
	@LiveRead
	public boolean isDeadLive();

	/**
	 * Returns the inventory of the entity
	 *
	 * @return inventory
	 */
	@SnapshotRead
	public Inventory getInventory();

	/**
	 * Returns the size of an entity's inventory
	 *
	 * @return inventorysize
	 */
	@SnapshotRead
	public int getInventorySize();

	/**
	 * Sets the size of an entity's inventory
	 *
	 * @return
	 */
	@DelayedWrite
	public void setInventorySize(int size);

	/**
	 * Sets the maximum distance at which the entity can be seen.<br>
	 * <br>
	 * The actual view distance used by the server may not be exactly the value
	 * that is set.
	 *
	 * @param distance the distance in blocks at which the entity can be seen
	 */
	@DelayedWrite
	public void setViewDistance(int distance);

	/**
	 * Gets the maximum distance at which the entity can be seen.<br>
	 *
	 * @return the distance in blocks at which the entity can be seen
	 */
	@SnapshotRead
	public int getViewDistance();
}
