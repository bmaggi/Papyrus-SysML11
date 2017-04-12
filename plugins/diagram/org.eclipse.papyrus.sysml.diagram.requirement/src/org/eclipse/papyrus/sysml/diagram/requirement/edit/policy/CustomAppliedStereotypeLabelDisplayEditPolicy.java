/*****************************************************************************
 * Copyright (c) 2012 ATOS.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Olivier Mélois (Atos) olivier.melois@atos.net - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.sysml.diagram.requirement.edit.policy;

import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeNodeLabelDisplayEditPolicy;

/**
 * Edit policy responsible of the display of stereotypes. It does not rely
 * on commands to get the stereotypes (the computation of the text was
 * formerly processed by commands executed when the object was dragged
 * from a palette, from instance).
 *
 * @author omelois
 */
public class CustomAppliedStereotypeLabelDisplayEditPolicy extends AppliedStereotypeNodeLabelDisplayEditPolicy {


}
