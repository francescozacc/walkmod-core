/* 
  Copyright (C) 2013 Raquel Pau and Albert Coroleu.
 
  Walkmod is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
 
  Walkmod is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.
 
  You should have received a copy of the GNU Lesser General Public License
  along with Walkmod.  If not, see <http://www.gnu.org/licenses/>.*/
package org.walkmod.commands;

import java.util.List;

import org.walkmod.OptionsBuilder;
import org.walkmod.WalkModFacade;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Removes a list of transformations from your configuration.")
public class RemoveTransformationCommand implements Command {

	@Parameter(description = "The list of transformation types", required = true)
	private List<String> types = null;

	@Parameter(names = "--help", help = true, hidden = true)
	private boolean help;

	private JCommander jcommander;

	@Parameter(names = { "--chain" }, description = "The chain name")
	private String chain = "default";
	
	@Parameter(names = {"--recursive", "-R"}, description = "Removes the transformation to all submodules")
	private boolean recursive = false;

	@Parameter(names = { "-e", "--verbose" }, description = "Prints the stacktrace of the produced error during the execution")
	private Boolean printErrors = false;

	public RemoveTransformationCommand(JCommander jcommander) {
		this.jcommander = jcommander;
	}

	@Override
	public void execute() throws Exception {
		if (help) {
			jcommander.usage("rm");
		} else {
			WalkModFacade facade = new WalkModFacade(OptionsBuilder.options().printErrors(printErrors));
			facade.removeTransformations(chain, types, recursive);
		}
	}

}
