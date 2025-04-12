package PRACTICE;

import java.util.Scanner;

public class RPG_GAME {

	static int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_exp, hero_gold;
	static int monster_level, monster_power, monster_hp, monster_defense, monster_mp, monster_exp, monster_gold;
	static String hero_name, monster_name;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hero_level = 1;
		hero_power = 15;
		hero_hp = 80;
		hero_defense = 25;
		hero_mp = 0;
		hero_exp = 0;
		hero_gold = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("영웅의 이름을 입력하세요. : ");
		hero_name = in.next();
		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");
		System.out.println("_________________");
		while (true) {
			if (hero_exp >= hero_level * 80) {
				int prevgold = hero_gold;
				hero_level += 1;
				System.out.println(hero_name + "의 레벨이" + hero_level + "이 되었습니다.");
				hero_gold += hero_level * 50;
				hero_exp = 0;
				System.out.println("레벨업 기념으로 돈이" + prevgold + "에서" + hero_gold + "이 되었습니다.");
			}
			System.out.println("현재 hero의 이름:" + hero_name);
			System.out.println("현재 " + hero_name + "의 레벨:" + hero_level);
			System.out.println("현재 " + hero_name + "의 힘:" + hero_power);
			System.out.println("현재 " + hero_name + "의 방어력:" + hero_defense);
			System.out.println("현재 " + hero_name + "의 HP:" + hero_hp);
			System.out.println("현재 " + hero_name + "의 MP:" + hero_mp);
			System.out.println("현재 " + hero_name + "의 경험치:" + hero_exp);
			System.out.println("현재 " + hero_name + "의 돈" + hero_gold + "원");
			System.out.println("_________________");
			System.out.println("1. 사냥터 \n2. 포션 상점");
			System.out.println("입장할 장소를 선택하세요.\n0 누르면 게임 종료!");
			int x = in.nextInt();
			if (x == 0) {
				System.out.println("게임을 종료합니다.");
				break;
			}
			if (x == 1) {
				System.out.println("사냥터에 입장하였습니다.");
				System.out.println("전투할 상대를 고르세요. 너구리/살쾡이");
				monster_name = in.next();
				if (monster_name.equals("너구리")) {
					monster_level = 1;
					monster_power = 20;
					monster_hp = 100;
					monster_defense = 5;
					monster_exp = 10;
					monster_gold = 10;
				}
				if (monster_name.equals("살쾡이")) {
					monster_level = 5;
					monster_power = 100;
					monster_hp = 2000;
					monster_defense = 20;
					monster_exp = 50;
					monster_gold = 30;
				}

				while (hero_hp > 0 && monster_hp > 0) {
					System.out.println("1.일반공격\n2.파괴광선");
					int n = in.nextInt();
					if (n == 1) {
						monster_attacked(hero_attack());

						if (monster_hp < 0) {
							System.out.println(monster_name + " 처치 완료!");
							hero_exp += monster_exp;
							hero_gold += monster_gold;
							break;
						}

						hero_attacked(monster_attack());
						if (hero_hp < 0) {
							System.out.println(hero_name + " 죽음!");
							System.out.println(hero_name + " 부활~");
							hero_hp = 80;

						}
						break;
					}
					if (n == 2) {
						monster_attacked(hero_skill());

						if (monster_hp < 0) {
							System.out.println(monster_name + " 처치 완료!");
							hero_exp += monster_exp;
							hero_gold += monster_gold;
							break;
						}

						hero_attacked(monster_attack());
						if (hero_hp < 0) {
							System.out.println(hero_name + " 죽음!");
							System.out.println(hero_name + " 부활~");
							hero_hp = 80;

						}
						break;
					}
				}
			}
			if (x == 2) {
				System.out.println("1. 힘 증강 포션 (30원)");
				System.out.println("2. 방어력 증강 포션 (30원)");
				System.out.println("3. 경험치 증강 포션 (100원)");
				System.out.println("4. HP 증강 포션 (10원)");
				System.out.println("5. MP 증강 포션 (10원)");
				System.out.println("원하시는 물건을 입력하세요:");
				int num = in.nextInt();
				potionStore_show(hero_gold, num);

			}
		}
	}

	static int hero_attack() {
		int sum = hero_level * 10 + hero_power * 30;
		System.out.println(hero_name + "의 공격!!");
		System.out.println(monster_name + "가 받는 데미지는" + sum);
		return sum;
	}

	static void hero_attacked(int sum) {

		if (hero_defense >= monster_power) {
			hero_hp = hero_hp;
		} else {
			hero_hp = hero_hp + hero_defense - monster_power;
		}
	}

	static int monster_attack() {
		System.out.println(monster_name + "의 침뱉기!");
		System.out.println(hero_name + "가 받는 데미지는" + monster_power);
		return monster_power;
	}

	static void monster_attacked(int sum) {
		if (monster_defense >= sum) {
			monster_hp = monster_hp;
		} else {
			monster_hp = monster_hp + monster_defense - sum;
		}

	}

	static int potionStore_show(int money, int num) {

		switch (num) {
		case 1:
			if (hero_gold >= 30) {
				System.out.println("힘 증강 포션 포션 구매");
				hero_gold -= 30;
				hero_power += 3;
			} else {
				System.out.println("구매 불가");
			}
			break;
		case 2:
			if (hero_gold >= 30) {
				System.out.println("방어력 증강 포션 구매");
				hero_gold -= 30;
				hero_defense += 3;
			} else {
				System.out.println("구매불가");
			}
			break;
		case 3:
			if (hero_gold >= 100) {
				System.out.println("경험치 증강 포션 구매");
				hero_gold -= 100;
				hero_exp += 50;
			} else {
				System.out.println("구매불가");
			}
			break;
		case 4: {
			if (hero_gold >= 10) {
				System.out.println("HP 증강 포션 포션 구매");
				hero_gold -= 10;
				hero_hp += 50;
			} else {
				System.out.println("구매불가");
			}
			break;
		}
		case 5: {
			if (hero_gold >= 10) {
				System.out.println("MP 증강 포션 포션 구매");
				hero_gold -= 10;
				hero_mp += 50;
			} else {
				System.out.println("구매불가");
			}
			break;
		}
		}
		return money;
	}

	static int hero_skill() {
		int skilldamage = 0;
		if (hero_mp >= 20) {
			skilldamage = hero_power * 100 + hero_level * 30;
			hero_mp -= hero_mp;
			System.out.println(hero_name + "가 파괴광선을 사용했습니다.");
		} else {
			System.out.println("마나 부족!");
			return 0;
		}
		return skilldamage;
	}

}
