package my.app;

import java.util.Objects;

public class Item {
	int item_id;
	int item_name;
	int item_price;
	
	public Item() {
		super();
	}
	
	public Item(int item_id, int item_name, int item_price) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_price = item_price;
	}
	
	protected int getItem_id() {
		return item_id;
	}
	protected void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	protected int getItem_name() {
		return item_name;
	}
	protected void setItem_name(int item_name) {
		this.item_name = item_name;
	}
	protected int getItem_price() {
		return item_price;
	}
	protected void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(item_id, item_name, item_price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return item_id == other.item_id && item_name == other.item_name && item_price == other.item_price;
	}
	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name + ", item_price=" + item_price + "]";
	}
	
	
}
