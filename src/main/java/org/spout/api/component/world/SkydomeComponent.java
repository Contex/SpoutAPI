/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * SpoutAPI is licensed under the Spout License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.api.component.world;

import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.model.Model;

public class SkydomeComponent extends WorldComponent {
	private final DefaultedKeyImpl<Model> SKYDOME_MODEL = new DefaultedKeyImpl<Model>("skydome", null);

	/**
	 * Gets the {@link Model} applied to this skydome.
	 * @return The model or null if no model is currently set
	 */
	public Model getModel() {
		return getDatatable().get(SKYDOME_MODEL);
	}

	/**
	 * Sets the {@link Model} for this skydome.
	 * @param model the new model of the skydome.
	 */
	public void setModel(final Model model) {
		getDatatable().put(SKYDOME_MODEL, model);
	}
}
