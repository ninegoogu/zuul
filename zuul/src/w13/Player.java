package w13;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Player {
	private Room currentRoom;
	private Room recentRoom;
	private ArrayList<Item> items;
	private int maxWeight;
	
	/**
	 * 구성자
	 * @param startRoom 이 선수가 처음 게임을 시작할 방
	 */
	public Player(Room startRoom, int maxWeight) {
		currentRoom = startRoom;
		recentRoom = startRoom;
		items = new ArrayList<>();
		this.maxWeight = maxWeight;
	}
	
	/**
	 * 주어진 방향으로 옮겨간다.
	 * 그 방향으로 출구가 없으면 현재 위치에 머문다.
	 * @param direction 옮겨갈 방향.
	 * @return 성공했으면 0, 실패했으면 -1.
	 */
	public int moveTo(String direction) {
		Room nextRoom = null;
		nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null)
			return -1;
		else {
			recentRoom = currentRoom;
			currentRoom = nextRoom;
			return 0;
		}
	}
	
	/**
	 * 이전 방으로 돌아간다.
	 */
	public void back() {
		currentRoom = recentRoom;
	}
	
	/**
	 * 아이템을 집는다.
	 * @param name 집어 들 아이템의 이름.
	 * @return 집어 든 아이템 (성공한 경우), null (실패한 경우).
	 */
	public Item takeItem(String name) {
		Item item = currentRoom.removeItem(name);
		
		if (item != null) {
			if (pickable(item))
				items.add(item);
			else {
				currentRoom.addItem(item);
				item = null;
			}
		}
		return item;
	}
	
	/**
	 * 가지고 있는 아이템 중 하나를 내려 놓는다.
	 * @param name 내려 놓을 아이템의 이름.
	 * @return 내려 놀은 아이템 (성공한 경우), null (실패한 경우).
	 */
	public Item dropItem(String name) {
		Iterator<Item> it = items.iterator();
		
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getName().equals(name)) {
				it.remove();
				currentRoom.addItem(item);
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 선수가 가지고 있는 아이템들의 list를 반환한다.
	 * 단, 반환되는 list는 수정할 수 없는 list이다.
	 * @return 선수가 가지고 있는 아이템들의 list.
	 */
	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}
	
	/**
	 * 선수가 현재 있는 방을 반환한다.
	 * @return 선수가 현재 있는 방.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * 이 Player가 들 수 있는 아이템의 최대 무게.
	 * @return
	 */
	public int getMaxWeight() {
		return maxWeight;
	}
	
	/**
	 * 지정된 아이템이 집어 들 수 있는 무게인지 판별한다. 집어들 수 있나?
	 * @param item 판별할 아이템
	 * @return 집어들 수 있으면 true, 그렇지 않으면 false.
	 */
	private boolean pickable(Item item) {
		if (item.getWeight() + totalWeight() > maxWeight)
			return false;
		else
			return true;
	}
	
	/*
	 * 이 선수가 가지고 있는 아이템들의 총 무게를 알아낸다.
	 */
	private int totalWeight() {
		Iterator<Item> it = items.iterator();
		int sum = 0;
		while (it.hasNext())
			sum += it.next().getWeight();
		return sum;
	}
}
