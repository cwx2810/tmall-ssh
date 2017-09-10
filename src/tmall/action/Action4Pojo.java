package tmall.action;

import java.util.List;

import tmall.pojo.Category;
import tmall.pojo.Order;
import tmall.pojo.OrderItem;
import tmall.pojo.Product;
import tmall.pojo.ProductImage;
import tmall.pojo.Property;
import tmall.pojo.PropertyValue;
import tmall.pojo.User;

//提供实体对象的getter、setter
public class Action4Pojo extends Action4Pagination{
    protected Category category;
    protected Property property;
    protected Product product;
    protected ProductImage productImage;
    protected PropertyValue propertyValue;
    protected User user;
    protected Order order;
    protected OrderItem orderItem;
    
    protected List<Category> categorys;
    protected List<Property> propertys;
    protected List<Product> products;
    protected List<ProductImage> productSingleImages;
    protected List<ProductImage> productDetailImages;
    protected List<PropertyValue> propertyValues;
    protected List<User> users;
    protected List<Order> orders;
    protected List<OrderItem> orderItems;
    
    //对外提供get方法，让jsp获取
    public Category getCategory() {
        return category;
    }
    //对外提供set方法，用于接收注入
    public void setCategory(Category category) {
        this.category = category;
    }
 
    public List<Category> getCategorys() {
        return categorys;
    }
 
    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public List<Property> getPropertys() {
		return propertys;
	}
	public void setPropertys(List<Property> propertys) {
		this.propertys = propertys;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public ProductImage getProductImage() {
		return productImage;
	}
	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}
	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}
	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}
	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}
	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}
	public PropertyValue getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(PropertyValue propertyValue) {
		this.propertyValue = propertyValue;
	}
	public List<PropertyValue> getPropertyValues() {
		return propertyValues;
	}
	public void setPropertyValues(List<PropertyValue> propertyValues) {
		this.propertyValues = propertyValues;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
    
}
