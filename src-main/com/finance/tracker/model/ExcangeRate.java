package com.finance.tracker.model;

public class ExcangeRate {
	private static double bgnToUds = 1.74441;
	private static double udsToBgn = 0.57326;
	private static double eurToBgn = 1.95;
	private static double eurToUds = 1.12;
	private static double udsToEur = 0.89;
	private static double bgnToEur = 0.51;


	public double convertMoney(double amount, Currency cuurentCurrency, Currency newCurrency) {
		if (amount > 0) {
			if (cuurentCurrency.equals(Currency.BGN) && newCurrency.equals(Currency.EUR)) {
				return converetFromBGNToEUR(amount);
			}
			if (cuurentCurrency.equals(Currency.BGN) && newCurrency.equals(Currency.UDS)) {
				return converetFromBGNToUDS(amount);
			}
			if (cuurentCurrency.equals(Currency.EUR) && newCurrency.equals(Currency.BGN)) {
				return converetFromEURToBGN(amount);
			}
			if (cuurentCurrency.equals(Currency.EUR) && newCurrency.equals(Currency.UDS)) {
				return converetFromEURToUDS(amount);
			}
			if (cuurentCurrency.equals(Currency.UDS) && newCurrency.equals(Currency.BGN)) {
				return converetFromUDSTOBGN(amount);
			}
			if (cuurentCurrency.equals(Currency.UDS) && newCurrency.equals(Currency.EUR)) {
				return converetFromUDSToEUR(amount);
			}
		}
		return 0;
	}

	private double converetFromEURToUDS(double eur) {
		if (eur > 0)
			return eur * eurToUds;
		else {
			return 0;
		}
	}

	private double converetFromEURToBGN(double eur) {
		if (eur > 0)
			return eur * eurToBgn;
		else {
			return 0;
		}
	}

	private double converetFromBGNToUDS(double bgn) {
		if (bgn > 0)
			return bgn * bgnToUds;
		else {
			return 0;
		}
	}

	private double converetFromBGNToEUR(double bgn) {
		if (bgn > 0)
			return bgn * bgnToEur;
		else {
			return 0;
		}
	}

	private double converetFromUDSTOBGN(double uds) {
		if (uds > 0)
			return uds * udsToBgn;
		else {
			return 0;
		}
	}

	private double converetFromUDSToEUR(double uds) {
		if (uds > 0)
			return uds * udsToEur;
		else {
			return 0;
		}
	}

}
