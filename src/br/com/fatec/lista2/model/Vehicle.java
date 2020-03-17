/*
        This file is part of FeiraAutomotiva.

        FeiraAutomotiva is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        FeiraAutomotiva is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Foobar.  If not, see <https://www.gnu.org/licenses/>.

 */
package br.com.fatec.lista2.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
  * Compreendendo a regra de negócio 03
  */
public class Vehicle {
        private String licensePlate;
        private String modelVersion;
        private String brand;
        private Calendar yearOfManufacture;
        private double purchasePrice;

        public Vehicle() {
                licensePlate = "";
                modelVersion = "";
                brand = "";
                yearOfManufacture = Calendar.getInstance();
                purchasePrice = 0.0;
        }
        // Impressão dos dados
        public void print() {
                System.out.println("Dados do Veículo.\n"
                        + "\tPlaca:             " + licensePlate + "\n"
                        + "\tMarca:             " + brand + "\n"
                        + "\tModelo/Versão:     " + modelVersion + "\n"
                        + "\tAno de Fabricação: " + yearOfManufacture.get(Calendar.YEAR) + "\n"
                        + "\tPreço de Compra:   " + purchasePrice
                );
        }
        /*
         * Getters e Setters
         */
        public String getLicensePlate() {
                return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
                this.licensePlate = licensePlate;
        }

        public String getModelVersion() {
                return modelVersion;
        }

        public void setModelVersion(String modelVersion) {
                this.modelVersion = modelVersion;
        }

        public String getBrand() {
                return brand;
        }

        public void setBrand(String brand) {
                this.brand = brand;
        }

        public Calendar getYearOfManufacture() {
                return yearOfManufacture;
        }

        public void setYearOfManufacture(Calendar yearOfManufacture) {
                this.yearOfManufacture = yearOfManufacture;
        }

        public void setPurchasePrice(double purchasePrice) {
                this.purchasePrice = purchasePrice;
        }

        public double getPurchasePrice() {
                return purchasePrice;
        }
}
