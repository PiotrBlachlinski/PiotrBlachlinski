package driver.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the car database table.
 * 
 */
@Entity
@NamedQuery(name="Car.findAll", query="SELECT c FROM Car c")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idcar;

	private String manufacturer;

	private String model;

	private int price;

	@ManyToOne
	@JoinColumn(name="status")
	private Status status;

	private String type;

//	//bi-directional one-to-one association to Status
//	@OneToMany(mappedBy="status")
//	private Status statusBean;
	
	

	public Car() {
	}

	public Integer getIdcar() {
		return this.idcar;
	}

	public void setIdcar(Integer idcar) {
		this.idcar = idcar;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public int getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

//	public Status getStatusBean() {
//		return this.statusBean;
//	}
//
//	public void setStatusBean(Status statusBean) {
//		this.statusBean = statusBean;
//	}
	
	

}