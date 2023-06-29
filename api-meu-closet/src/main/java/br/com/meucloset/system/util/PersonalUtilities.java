package br.com.meucloset.system.util;

import org.springframework.stereotype.Service;

@Service
public class PersonalUtilities {
	
	public boolean validatorCpf(String cpf) {
		boolean result = false;
		if (cpf == null) {
			return false;
		}
		int dig1 = 0, dig2 = 0, const1 = 10, const2 = 11;
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");

		if (cpf.length() == 11) {
			for (int i = 0; i <= 9; i++) {
				if (!cpf.substring(i, i + 1).equals(cpf.substring(i + 1, i + 2))
						|| !cpf.substring(0, 1).equals(cpf.substring(i + 1, i + 2))) {
					result = true;
					break;
				}
			}
			if (result) {
				for (int x = 0; x <= 9; x++) {
					int digit = Integer.parseInt(cpf.substring(x, x + 1));
					if (x <= 8) {
						dig1 += digit * const1;
						const1 -= 1;
					}
					dig2 += digit * const2;
					const2 -= 1;
				}
			}
			if (result) {
				result = false;
				String digVer = String.valueOf((11 - (dig1 % 11) <= 9) ? 11 - (dig1 % 11) : 0);
				digVer += String.valueOf((11 - (dig2 % 11) <= 9) ? 11 - (dig2 % 11) : 0);
				if (cpf.substring(9, 11).equals(digVer))
					result = true;
			}
		}
		return result;
	}

}
