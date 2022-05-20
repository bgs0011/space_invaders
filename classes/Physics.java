package game.classes;

public class Physics {
	public static boolean Collusion(EntityA entA, EntityB entB) {

		if (entA.getBounds().intersects(entB.getBounds())) {
			return true;
		}

		return false;
	}

	public static boolean Collusion(EntityB entB, EntityA entA) {

		if (entB.getBounds().intersects(entA.getBounds())) {
			return true;
		}

		return false;
	}
}