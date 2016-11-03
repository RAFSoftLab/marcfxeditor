package rs.edu.raf.marcfx.marc21records;

import java.text.DecimalFormat;

/**
 * Represents a LEADER in a MARC21 record.
 * Leader is a fixed field that comprises the first 24 character positions 
 * (00-23) of each record and provides information for the processing of the record.  
 * 
 * @author bojana
 */

public class Leader implements java.io.Serializable{	
	
	static private final String UNDEFINED_NUMBER_STR = "*****"; 
	static private final int UNDEFINED_NUMBER = 0;
	
	/** 00-04 - Record length (number)*/
	String recordLength;
	/** 05 - Record status (code)*/
	char recordStatus;
	/** 06 - Type of record (code)*/
	char recordType;
	/** 07 - Bibliographic level (code)*/
	char bibliographicLevel;
	/** 08 - Type of control (code) */
	char controlType;
	/** 09 - Character coding scheme */
	char characterCodingScheme;
	/** 10 - Indicator count (number) */
	int indicatorCount;
	/** 11 - Subfield code count (number)*/
	int subfieldCodeCount;
	/** 12-16 - Base address of data (number)*/
	int dataBaseAddress;
	/** 17 - Encoding level (code) */
	char encodingLevel;
	/** 18 - Descriptive cataloging form (code) */
	char descriptiveCatalogingForm;
	/** 19 - Multipart resource record level  (code) */
	char multipartResourceRecordLevel;	
	/** 20-23 - Entry map */
	/** 20 - Length of the length-of-field portion (number) */
	int lengthOfFieldPortion;
	/** 21 - Length of the starting-character-position portion(number) */
	int startingCharacterPositionPortion;
	/** 22 - Length of the implementation-defined portion (number) */
	int implementationDefinedPortion;
	/** 23 - Undefined Entry map character position (number) */
	int undefinedEntryMapCharacterPosition;
	
	/** number format for both record length and base address of data */
    DecimalFormat df = new DecimalFormat("00000");
	
	/**
	  * Default constructor.
	  */
	public Leader() {
		recordLength = UNDEFINED_NUMBER_STR;
		recordStatus = 'n';
		recordType = 'a';
		bibliographicLevel = 'm';
		controlType = '#';
		characterCodingScheme = '#';
		indicatorCount = 2;
		subfieldCodeCount = 2;
		dataBaseAddress = UNDEFINED_NUMBER;
		encodingLevel = '#';
		descriptiveCatalogingForm = 'i';
		multipartResourceRecordLevel = '#';
		lengthOfFieldPortion = 4;
		startingCharacterPositionPortion = 5;
		implementationDefinedPortion = 0;
		undefinedEntryMapCharacterPosition = 0;	
	}
	
	/**
	 * @param leader
	 */
	public Leader(String content) {		
		parseContent(content);
	}
	
	/**
	 * 
	 * (attributes from oai_marc element according to oai_marc scheme) 
	 * 
	 */
	public Leader(char status,  char type, char level, char ctlType,  char charEnc, char encLvl,  char catForm, char lrRqrd) {
		bibliographicLevel = level;
		characterCodingScheme = charEnc;
		controlType = ctlType;
		descriptiveCatalogingForm = catForm;
		encodingLevel = encLvl;
		multipartResourceRecordLevel = lrRqrd;
		recordStatus = status;
		recordType = type;
		
//		 default values
		recordLength = UNDEFINED_NUMBER_STR;
		dataBaseAddress = UNDEFINED_NUMBER;
		indicatorCount = 2;
		subfieldCodeCount = 2;
		lengthOfFieldPortion = 4;
		startingCharacterPositionPortion = 5;
		implementationDefinedPortion = 0;
		undefinedEntryMapCharacterPosition = 0;		
	}

	
	/** parsira ceo string i dodeljuje vrednost poljima koji su deo lidera*/
	private void parseContent(String content){		
		recordStatus = content.charAt(5);
		recordType = content.charAt(6);
		bibliographicLevel = content.charAt(7);
		controlType = content.charAt(8);
		characterCodingScheme = content.charAt(9);
		indicatorCount = Integer.valueOf(content.substring(10,11));
		subfieldCodeCount = Integer.valueOf(content.substring(11,12));
		
		encodingLevel = content.charAt(17);
		descriptiveCatalogingForm = content.charAt(18);
		multipartResourceRecordLevel = content.charAt(19);
		
		lengthOfFieldPortion = Integer.valueOf(content.substring(20,21));
		startingCharacterPositionPortion = Integer.valueOf(content.substring(21,22));
		implementationDefinedPortion = Integer.valueOf(content.substring(22,23));;
		undefinedEntryMapCharacterPosition = Integer.valueOf(content.substring(23));
		
		try {
			recordLength = content.substring(0,5);			
		} catch (NumberFormatException e) {			
			recordLength = UNDEFINED_NUMBER_STR;			
		}
		try {
			dataBaseAddress = Integer.parseInt(content.substring(12,17));
		} catch (NumberFormatException e) {
			dataBaseAddress = UNDEFINED_NUMBER;
		}
	}
	
	/** delove content-a sakuplja u jedan string */
	private String gatherContent(){
		StringBuffer buff = new StringBuffer();
		if(recordLength==UNDEFINED_NUMBER_STR)
			buff.append("*****");
		else buff.append(df.format(recordLength).toString());
		buff.append(recordStatus)
			.append(recordType)
			.append(bibliographicLevel)
			.append(controlType)
			.append(characterCodingScheme)
			.append(indicatorCount)
			.append(subfieldCodeCount);
		if (dataBaseAddress ==UNDEFINED_NUMBER)
			buff.append("     ");
		else
			buff.append(df.format(dataBaseAddress).toString());
		buff.append(encodingLevel)
			.append(descriptiveCatalogingForm)
			.append(multipartResourceRecordLevel)
			.append(lengthOfFieldPortion)
			.append(startingCharacterPositionPortion)
			.append(implementationDefinedPortion)
			.append(undefinedEntryMapCharacterPosition);					
		return buff.toString();
		
	}
	
	
	
	public String getContent() {
		return gatherContent();
	}
	public void setContent(String content) {		 
		parseContent(content);
	}
	
	
	
	

	/**
	  * Returns a printable string representation of this content.
	  */
	public String toFullFormatString() {
	    return "LDR    "+gatherContent().replace(' ','*');
	}

	/**
	 * @return Returns the bibliographicLevel.
	 */
	public char getBibliographicLevel() {
		return bibliographicLevel;
	}

	/**
	 * @param bibliographicLevel The bibliographicLevel to set.
	 */
	public void setBibliographicLevel(char bibliographicLevel) {
		this.bibliographicLevel = bibliographicLevel;
	}

	/**
	 * @return Returns the characterCodingScheme.
	 */
	public char getCharacterCodingScheme() {
		return characterCodingScheme;
	}

	/**
	 * @param characterCodingScheme The characterCodingScheme to set.
	 */
	public void setCharacterCodingScheme(char characterCodingScheme) {
		this.characterCodingScheme = characterCodingScheme;
	}

	/**
	 * @return Returns the controlType.
	 */
	public char getControlType() {
		return controlType;
	}

	/**
	 * @param controlType The controlType to set.
	 */
	public void setControlType(char controlType) {
		this.controlType = controlType;
	}

	/**
	 * @return Returns the dataBaseAddress.
	 */
	public int getDataBaseAddress() {
		return dataBaseAddress;
	}

	/**
	 * @param dataBaseAddress The dataBaseAddress to set.
	 */
	public void setDataBaseAddress(int dataBaseAddress) {
		this.dataBaseAddress = dataBaseAddress;
	}
	
	public void setDataBaseAddress(String str){
		try{
			this.dataBaseAddress = Integer.valueOf(str);
		}catch(NumberFormatException e){
		 this.dataBaseAddress = UNDEFINED_NUMBER;
		}
	}

	/**
	 * @return Returns the descriptiveCatalogingForm.
	 */
	public char getDescriptiveCatalogingForm() {
		return descriptiveCatalogingForm;
	}

	/**
	 * @param descriptiveCatalogingForm The descriptiveCatalogingForm to set.
	 */
	public void setDescriptiveCatalogingForm(char descriptiveCatalogingForm) {
		this.descriptiveCatalogingForm = descriptiveCatalogingForm;
	}

	/**
	 * @return Returns the df.
	 */
	public DecimalFormat getDf() {
		return df;
	}

	/**
	 * @param df The df to set.
	 */
	public void setDf(DecimalFormat df) {
		this.df = df;
	}

	/**
	 * @return Returns the encodingLevel.
	 */
	public char getEncodingLevel() {
		return encodingLevel;
	}

	/**
	 * @param encodingLevel The encodingLevel to set.
	 */
	public void setEncodingLevel(char encodingLevel) {
		this.encodingLevel = encodingLevel;
	}

	/**
	 * @return Returns the implementationDefinedPortion.
	 */
	public int getImplementationDefinedPortion() {
		return implementationDefinedPortion;
	}

	/**
	 * @param implementationDefinedPortion The implementationDefinedPortion to set.
	 */
	public void setImplementationDefinedPortion(int implementationDefinedPortion) {
		this.implementationDefinedPortion = implementationDefinedPortion;
	}

	/**
	 * @return Returns the indicatorCount.
	 */
	public int getIndicatorCount() {
		return indicatorCount;
	}

	/**
	 * @param indicatorCount The indicatorCount to set.
	 */
	public void setIndicatorCount(int indicatorCount) {
		this.indicatorCount = indicatorCount;
	}

	/**
	 * @return Returns the lengthOfFieldPortion.
	 */
	public int getLengthOfFieldPortion() {
		return lengthOfFieldPortion;
	}

	/**
	 * @param lengthOfFieldPortion The lengthOfFieldPortion to set.
	 */
	public void setLengthOfFieldPortion(int lengthOfFieldPortion) {
		this.lengthOfFieldPortion = lengthOfFieldPortion;
	}

	/**
	 * @return Returns the multipartResourceRecordLevel.
	 */
	public char getMultipartResourceRecordLevel() {
		return multipartResourceRecordLevel;
	}

	/**
	 * @param multipartResourceRecordLevel The multipartResourceRecordLevel to set.
	 */
	public void setMultipartResourceRecordLevel(char multipartResourceRecordLevel) {
		this.multipartResourceRecordLevel = multipartResourceRecordLevel;
	}

	/**
	 * @return Returns the recordLength.
	 */
	public String getRecordLength() {
		return recordLength;
	}

	/**
	 * @param recordLength The recordLength to set.
	 */
	public void setRecordLength(String recordLength) {
		this.recordLength = recordLength;
	}

	/**
	 * @return Returns the recordStatus.
	 */
	public char getRecordStatus() {
		return recordStatus;
	}

	/**
	 * @param recordStatus The recordStatus to set.
	 */
	public void setRecordStatus(char recordStatus) {
		this.recordStatus = recordStatus;
	}

	/**
	 * @return Returns the recordType.
	 */
	public char getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType The recordType to set.
	 */
	public void setRecordType(char recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return Returns the startingCharacterPositionPortion.
	 */
	public int getStartingCharacterPositionPortion() {
		return startingCharacterPositionPortion;
	}

	/**
	 * @param startingCharacterPositionPortion The startingCharacterPositionPortion to set.
	 */
	public void setStartingCharacterPositionPortion(
			int startingCharacterPositionPortion) {
		this.startingCharacterPositionPortion = startingCharacterPositionPortion;
	}

	/**
	 * @return Returns the subfieldCodeCount.
	 */
	public int getSubfieldCodeCount() {
		return subfieldCodeCount;
	}

	/**
	 * @param subfieldCodeCount The subfieldCodeCount to set.
	 */
	public void setSubfieldCodeCount(int subfieldCodeCount) {
		this.subfieldCodeCount = subfieldCodeCount;
	}

	/**
	 * @return Returns the undefinedEntryMapCharacterPosition.
	 */
	public int getUndefinedEntryMapCharacterPosition() {
		return undefinedEntryMapCharacterPosition;
	}

	/**
	 * @param undefinedEntryMapCharacterPosition The undefinedEntryMapCharacterPosition to set.
	 */
	public void setUndefinedEntryMapCharacterPosition(
			int undefinedEntryMapCharacterPosition) {
		this.undefinedEntryMapCharacterPosition = undefinedEntryMapCharacterPosition;
	}
	
	public String toXtextString(){
		StringBuffer buff = new StringBuffer();
		buff.append("LDR ");		
		if(recordLength.equals(UNDEFINED_NUMBER_STR))
			buff.append("*****");
		else buff.append(df.format(recordLength).toString());
		buff.append(" ");
		buff.append(recordStatus+" ")
			.append(recordType+" ")
			.append(bibliographicLevel+" ")
			.append(controlType+" ")
			.append(characterCodingScheme+" ")
			.append(indicatorCount+" ")
			.append(subfieldCodeCount+" ");
		if (dataBaseAddress ==UNDEFINED_NUMBER)
			buff.append("#####");
		else
			buff.append(df.format(dataBaseAddress).toString());
		buff.append(" ");
		buff.append(encodingLevel+" ")
			.append(descriptiveCatalogingForm+" ")
			.append(multipartResourceRecordLevel+" ")
			.append(lengthOfFieldPortion+" ")
			.append(startingCharacterPositionPortion+" ")
			.append(implementationDefinedPortion+" ")
			.append(undefinedEntryMapCharacterPosition+"\r");					
		return buff.toString();
	}
	

	
	

	
	

}
