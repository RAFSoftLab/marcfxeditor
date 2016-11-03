package rs.edu.raf.marcfx.marc21format;



public class ExecuteLoadXMLFormat {
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Format format = FormatFactory.getFormat(FormatFactory.class
				.getResourceAsStream("/rs/edu/raf/marcfx/marc21format/xmlfiles/MARC21_format-bibliographic.xml"));
		System.out.println(format.getFLeader().getName());
		for(FCharPosition cp:format.getFLeader().getFCharPositions()){
			System.out.println(cp.getStart()+"-"+cp.getEnd()+"-"+cp.getDescription());
		}
		for(FField f:format.getFields()){			
			System.out.println(f.getName());			
		}

	}

}
