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

package org.jts.gui.autoUpdateCode;

import java.io.*;

/** Implements updates that need to be made to all model classes. 
*
*/
class AllUpdates {
  AllUpdates() {
    //System.out.println("updating auto-generated code... ");
    String srcDirString = new String("src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated");
    File srcDir = new File(srcDirString);
    
    FilenameFilter filter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return !name.contains(".svn");
        }
    };
    String [] children = srcDir.list(filter);
    
    for( int ii=0; ii<children.length; ii++ ) {
      try {
        // general statement
        FindReplace.replaceInFile( srcDirString + File.separator + children[ii], 
                                                 srcDirString + File.separator + children[ii], 
                                                 "Auto generated code by umlc", 
                                                 "This code has been auto-updated by JTS after it has been auto-generated by umlc" );
        // imports
        FindReplace.replaceInFile( srcDirString + File.separator + children[ii], 
                                                 srcDirString + File.separator + children[ii], 
                                                 "import com.u2d.persist.Persist;", 
                                                 "import javax.persistence.Entity;\r\n"+
                                                 "/** place holder for more imports **/\r\n");
        FindReplace.replaceInFile( srcDirString + File.separator + children[ii], 
                                                 srcDirString + File.separator + children[ii], 
                                                 "@Persist", 
                                                 "@Entity" );
        // validation
        FindReplace.replaceInFile( srcDirString + File.separator + children[ii], 
                                                 srcDirString + File.separator + children[ii], 
                                                 "/******************************************************************************/",
                                                 "/**********************/\r\n" +
                                                  "/**@Cmd place holder**/");

        // extension
        FindReplace.replaceInFile( srcDirString + File.separator + children[ii],
                                                 srcDirString + File.separator + children[ii],
                                                 "extends AbstractComplexEObject ",
                                                 "extends AbstractComplexEObject_JTS");
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
    }
  } 
  
  public static void main( String [] args ) {
    new JAXBUpdates();  
    new AllUpdates();
    new ArrayUpdates();
    new BitFieldUpdates();
    new BitRangeUpdates();
    new BodyUpdates();
    new ComplexFieldUpdates();
    new ComponentUpdates();
    new ConstantSetUpdates();
    new ConstantUpdates();
    new EventDefUpdates();
    new FixedFieldUpdates();
    new FixedLengthStringUpdates();
    new FormatEnumUpdates();
    new FooterUpdates();
    new HeaderUpdates();
    new InputSetUpdates();
    new ListUpdates();
    new MessageDefUpdates();
    new OutputSetUpdates();
    new ProtocolBehaviorUpdates();
    new RecordUpdates();
    new ScaleRangeUpdates();
    new SequenceUpdates();
    new ServiceDefUpdates();
    new ServiceSetUpdates();
    new SimpleFieldUpdates();
    new SubFieldUpdates();
    new TypeAndUnitsEnumUpdates();
    new ValueEnumUpdates();
    new ValueSetUpdates();
    new ValueRangeUpdates();
    new VariableFieldUpdates();
    new VariableFormatFieldUpdates();
    new VariableLengthFieldUpdates();
    new VariableLengthStringUpdates();
    new VariantUpdates();
  }
}