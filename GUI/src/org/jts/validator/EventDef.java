/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

package org.jts.validator;

public class EventDef 
{
	/**
	 * Validates a JSIDL event definition element.
	 * <pre>
		event_def =
		   element event_def {
		      attribute name { identifier },
				# 1. Must follow ANSI C syntax             
		      element description {  attribute xml:space { "default" | "preserve" }?, text },
		      (header | declared_header),
		      (body | declared_body),
		      (footer | declared_footer)
		   }
	 * </pre>
  	 * @param event JSIDL binding object of the event definition to be checked
	 * @throws ValidatorException if of the semantic checks above fail
	 */
	public static void validate(org.jts.jsidl.binding.EventDef event ) throws ValidatorException
	{
		Validator.validateName(event.getName());
		
		if (event.getHeader() == null)
		{
				throw new ValidatorException("Header must be defined, even if empty");
		}

		if (event.getBody() == null)
		{
				throw new ValidatorException("Body must be defined, even if empty");
		}

		if (event.getFooter() == null)
		{
				throw new ValidatorException("Footer must be defined, even if empty");
		}		
	}

}