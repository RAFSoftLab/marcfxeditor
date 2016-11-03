package rs.edu.raf.marcfx.marc21format;

import java.util.ArrayList;
import java.util.List;


public class FormatExpressionsFactory {
	
	public static List getDataFieldsProposals(){	
		//org.openarchitectureware.xtext.editor.contentassist.codeassist.Proposal
		//Proposal p =  
		
		StringBuffer buff = new StringBuffer();
		buff.append("{");
		List<FField> fields = FormatFactory.getMARC21BibliographicFormat().getFields();
		for  (int i=0;i<fields.size();i++){
			FField f = fields.get(i);
			if(f instanceof FDataField){
				buff.append("newProposal(");
				buff.append(f.getName()+"-"+f.getDescription());
				buff.append(",");
				buff.append(f.getName());
				buff.append("}");
				if(i<fields.size()-1)
					buff.append(",\n");
				else
					buff.append("}\n");
			}
		}
		System.out.println(buff.toString());
		return null;
	}
	
	public static List getDataFields(){
	 List	retList = new ArrayList();	
		List<FField> fields = FormatFactory.getMARC21BibliographicFormat().getFields();
		for  (int i=0;i<fields.size();i++){
			FField f = fields.get(i);
			if(f instanceof FDataField)
				retList.add(f);
		}
		return retList;
	}
	
	
	
	
}
