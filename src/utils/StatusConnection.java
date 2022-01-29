package utils;

public enum StatusConnection {
	CONNECTED("CONECTADO"),
	DISCONNECTED( "DESCONECTADO");

	private String description;
	 
	StatusConnection(String type) {
		this.description = type;
	}
	
	public String getDescricao() {
        return description;
    }

}
