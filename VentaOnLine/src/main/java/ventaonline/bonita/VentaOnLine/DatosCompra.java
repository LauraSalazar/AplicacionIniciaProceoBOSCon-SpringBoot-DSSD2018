package ventaonline.bonita.VentaOnLine;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DatosCompra {
	@XmlElement
	private String idCupon;
	@XmlElement
	private String idEmpleado;
	@XmlElement
	private String idProducto;
	
	public String getIdCupon() {
		return idCupon;
	}
	public void setIdCupon(String idCupon) {
		this.idCupon = idCupon;
	}
	public String getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	public DatosCompra(String idCupon, String idEmpleado, String idProducto) {
		super();
		this.idCupon = idCupon;
		this.idEmpleado = idEmpleado;
		this.idProducto = idProducto;
	}
	
	

}
