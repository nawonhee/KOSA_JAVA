

interface Weapon{
	void attack();
}

class Bow implements Weapon{
	public void attack() {
		System.out.println("활로 공격한다");
	}
}
class Sword implements Weapon{
	public void attack() {
		System.out.println("칼로 공격한다");
	}
}

class Character{
	Weapon weapon;
	
	void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}

class Main{
	public static void main(String[] args) {
		Character c = new Character();
		
		Weapon weapon = new Sword();
		c.setWeapon(weapon);
		weapon.attack();
	}
}