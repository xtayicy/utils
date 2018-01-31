package harry.utils;

import java.util.List;

/**
 * 
 * @author harry
 *
 */
public final class MathUtil {
	private MathUtil() {
	}

	public static boolean isArithmeticProgression(int[] arrs) {
		Integer temp = null, gap = null;
		boolean flag = false;
		int index = arrs == null ? 0 : arrs.length - 1;

		while (index > 1) {
			gap = arrs[index] - arrs[index - 1];
			if (arrs.length - 1 == index) {
				temp = gap;
			} else {
				return !(gap != null && gap != temp);
			}

			index--;
			gap = null;

			flag = index == 1;
		}

		return flag;
	}
}
