
	  {
		MsgIn.Body.List4 List = new MsgIn.Body.List4();
		MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
		MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
		MsgIn.Body.List4.Variant1 V3 = new MsgIn.Body.List4.Variant1();
		MsgIn.Body.List4.Variant1.List1.Record2 R1 = new MsgIn.Body.List4.Variant1.List1.Record2();
		MsgIn.Body.List4.Variant1.List1.Record2 R2 = new MsgIn.Body.List4.Variant1.List1.Record2();

		m_MsgIn1.getBody().setList4( List );
		
		V1.setFieldValue((short) 0);
		V1.getRecord1().setField1((short) 1);
		V1.getRecord1().setField2((short) 770);

		V2.setFieldValue((short) 1);
		R1.setField1((short) 3); V2.getList1().addElement(R1);
		R2.setField1((short) 4); V2.getList1().addElement(R2);

		V3.setFieldValue((short) 2);
		V3.getSequence1().getRecord3().setField1((short) 3);
		V3.getSequence1().getRecord3().setField2((short) 1284);

		m_MsgIn1.getBody().getList4().addElement(V1);
		m_MsgIn1.getBody().getList4().addElement(V2);
		m_MsgIn1.getBody().getList4().addElement(V3);
	  }
     


  
  public void testSetGetOperations()
  {
    System.out.println("");
    MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
    MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
    
    V1.getRecord1().setField1((short) 5);
    V1.getRecord1().setField2((short) 500);
	V1.setFieldValue((short) 0);
    V2.getSequence1().getRecord3().setField1((short) 6);
    V2.getSequence1().getRecord3().setField2((short) 600);
	V2.setFieldValue((short) 2);
    m_MsgIn1.getBody().getList4().addElement(V1);
    m_MsgIn2.getBody().getList4().addElement(V2);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getRecord1().getField1(), 5);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getRecord1().getField2(), 500);
    assertEquals(m_MsgIn2.getBody().getList4().getElement(0).getSequence1().getRecord3().getField1(), 6);
    assertEquals(m_MsgIn2.getBody().getList4().getElement(0).getSequence1().getRecord3().getField2(), 600);

	// Test copy constructor
    m_MsgIn2 = m_MsgIn1;
  }
  
  public void testEquality() {

  public void testEncodeToSpec()
  {
    ByteBuffer buff = ByteBuffer.allocate(1024);
    int i, j;
       
    System.out.println("");

    populate();
    m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
    m_MsgIn1.encode(buff, 0);

    assertEquals( buff.get(0) , 0 ); assertEquals( buff.get(1) , 1 ); // msg_id
	assertEquals( buff.get(2) , 3 );                                // str length
	for (i=3, j=0; i<7; i++, j++) assertEquals( buff.get(i) , j); // record type
	for (i=7, j=1; i<11; i++, j++) assertEquals( buff.get(i) , j); // list type
	for (i=11, j=2; i<15; i++, j++) assertEquals( buff.get(i) , j); // sequence type
  }
  
  public void testEncodeDecodeOperations()
  {
    int i; 
    ByteBuffer buff = ByteBuffer.allocate(1024);
    MsgIn.Body.List4 List = new MsgIn.Body.List4();
	MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
    MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
    MsgIn.Body.List4.Variant1 V3 = new MsgIn.Body.List4.Variant1();
    
    for (i=0;i<1024;i++)
       buff.put(i, (byte)(((2101*i)+65543) % 256));
    
    System.out.println("");
    
    // verifying message level encode/decode reciprocity"
    m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
    m_MsgIn1.getBody().setList4( List );
    m_MsgIn1.getBody().getList4().addElement(V1);
    m_MsgIn1.getBody().getList4().addElement(V2);
	m_MsgIn1.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn1.getBody().getList4().getElement(0).getRecord1().setField1((short) 15);
    m_MsgIn1.getBody().getList4().getElement(0).getRecord1().setField2((short) 300);
	m_MsgIn1.getBody().getList4().getElement(1).setFieldValue((short) 2);
    m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().setField1((short) 0);
    m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().setField2((short) 6525);
    
    m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
    m_MsgIn2.getBody().getList4().addElement(V3);
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 127);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 127);
    
    m_MsgIn1.encode(buff, 0);
    m_MsgIn2.decode(buff, 0);

    assertEquals( m_MsgIn2.getHeader().getHeaderRec().getMessageID() , 41);
    assertEquals( m_MsgIn2.getBody().getList4().getNumberOfElements(), 2);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getFieldValue() , 2);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 6525);
    
    
    // verifying header level encode/decode reciprocity
    
    m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
    m_MsgIn1.getHeader().encode(buff, 0);
    m_MsgIn2.getHeader().decode(buff, 0);
    assertEquals( m_MsgIn2.getHeader().getHeaderRec().getMessageID() , 41);

    m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
    m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
    m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
    assertEquals( m_MsgIn2.getHeader().getHeaderRec().getMessageID() , 41);
    
    //     --- verifying body level encode/decode reciprocity

	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 255);
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField2((short) 255);
    V3.getRecord1().setField1((short) 255); V3.getRecord1().setField2((short) 255);
	m_MsgIn2.getBody().getList4().addElement(V3);
    
    m_MsgIn1.getBody().encode(buff, 0);
    m_MsgIn2.getBody().decode(buff, 0);
    
    assertEquals( m_MsgIn2.getBody().getList4().getNumberOfElements(),2);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getFieldValue() , 2);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 6525);


    //     --- verifying list level encode/decode reciprocity
    
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 255);
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 1);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField2((short) 255);
    V3.getRecord1().setField1((short) 255); V3.getRecord1().setField2((short) 255);
    m_MsgIn2.getBody().getList4().addElement(V3);
    
    m_MsgIn1.getBody().getList4().encode(buff, 0);
    m_MsgIn2.getBody().getList4().decode(buff, 0);
    
    assertEquals( m_MsgIn2.getBody().getList4().getNumberOfElements(),2);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue(),0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getFieldValue(),2);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 6525);
    
    //     --- verifying record level encode/decode reciprocity
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 255);
    
    m_MsgIn1.getBody().getList4().getElement(0).encode(buff, 0);
    m_MsgIn2.getBody().getList4().getElement(0).decode(buff, 0);
    
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue(),0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
       
  }



  public void testListOperations()
  {
	MsgIn.Body.List4 List = new MsgIn.Body.List4();
    MsgIn.Body.List4.Variant1 V1;
	m_MsgIn2.getBody().setList4( List );
    int i,j;
    
    System.out.println("");
    
    //     --- verifying MsgIn getNumberOfElements with Add and Delete operations    
    i=j=0;  
    for (i=0;i<4;i++) {
	   V1 = new MsgIn.Body.List4.Variant1();
       assertEquals(m_MsgIn2.getBody().getList4().getNumberOfElements(),i);
       m_MsgIn2.getBody().getList4().addElement(V1);
    }   
    for (j=0,i=4;j<4;j++,i--) {
       assertEquals(m_MsgIn2.getBody().getList4().getNumberOfElements() , i);
       m_MsgIn2.getBody().getList4().deleteLastElement();
    }

    //     --- verifying deleteElement preserves order & corrects count of remaining elements                          
    
	populate();

    m_MsgIn1.getBody().getList4().deleteElement(0);
    assertEquals(m_MsgIn1.getBody().getList4().getNumberOfElements() , 2);

	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getFieldValue() , 1);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getNumberOfElements() , 2);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(0).getField1() , 3);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(1).getField1() , 4);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(1).getFieldValue() , 2);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 3);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 1284);
    
    m_MsgIn1.getBody().getList4().deleteElement(1);
    assertEquals(m_MsgIn1.getBody().getList4().getNumberOfElements() , 1);
    
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getFieldValue() , 1);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getNumberOfElements() , 2);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(0).getField1() , 3);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(1).getField1() , 4);
    
    m_MsgIn1.getBody().getList4().deleteElement(0);
    assertEquals(m_MsgIn1.getBody().getList4().getNumberOfElements() , 0);
                
  }

     public static void main(String[] args) {