package w13;

/**
 * This class is part of the "World of Zuul" application.
 */
public class Item {
	
	private String name;
	private String description;
	private int weight;
	
	/**
	 * Item 객체를 구성한다. 이름과 설명, 무게가 제공되어야 한다.
	 * @param name 이 아이템의 이름.
	 * @param description 이 아이템의 설명.
	 * @param weight 이 아이템의 무게.
	 */
	public Item(String name, String description, int weight) {
		this.name = name;
		this.description = description;
		this.weight = weight;
	}
	
	/**
	 * Item의 이름을 반환한다.
	 * @return 이 아이템의 이름.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Item에 대한 설명을 반환한다.
	 * @return 이 아이템의 설명.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Item의 무게를 반환한다.
	 * @return 이 아이템의 무게.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * 이 아이템에 대한 상세한 설명을 반환한다.
	 * @return 이 아이템에 대한 상세한 설명이 들어 있는 문자열.
	 */
	public String getLongDescription() {
		return name + " (" + weight + "Kg, " + description + ")";
	}

}
