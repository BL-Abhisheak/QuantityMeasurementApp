package com.quantity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantitymeasurementappApplication {

		public static class Feet {
			private final double value;

			public Feet(double value) {
				this.value = value;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj) return true;
				if (obj == null || getClass() != obj.getClass()) return false;
				Feet feet = (Feet) obj;
				return Double.compare(this.value, feet.value) == 0;
			}

			@Override
			public int hashCode() {
				return Double.hashCode(value);
			}
		}

		public static boolean compareFeet(double a, double b) {
			return new Feet(a).equals(new Feet(b));
		}

		public static void main(String[] args) {
			System.out.println(compareFeet(1.0, 1.0));
			System.out.println(compareFeet(1.0, 2.0));
		}

}
