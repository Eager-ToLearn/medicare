package pac.dto;

public class ProductDTO {
	
	private int id;
	private String name;
	private int category_Id;
	private double price;
	private double size;
	private String about;
	private String imageName;
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(int id, String name, int category_Id, double price, double size, String about, String imageName) {
		super();
		this.id = id;
		this.name = name;
		this.category_Id = category_Id;
		this.price = price;
		this.size = size;
		this.about = about;
		this.imageName = imageName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory_Id() {
		return category_Id;
	}
	public void setCategory_Id(int category_Id) {
		this.category_Id = category_Id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", category_Id=" + category_Id + ", price=" + price
				+ ", size=" + size + ", about=" + about + ", imageName=" + imageName + "]";
	}
	
	
	

}
