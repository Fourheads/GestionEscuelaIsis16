/*
 * This is a software made for highschool management 
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package fixture.simple;

import java.util.Random;

public class GenericData {
	
	private static String Nombre="Adrian,Manuel,Jose,Gogo,Pedro,Mariano,Leandro,Gonzalo,Roberto,Daniel,Fernando,Damian,Oscar,Lautaro,Miguel,Diego,Pablo,Raul,Ricardo,Matias,Hector,Juan,Emiliano,Cesar,Gerardo";
	private static String Apellido="Garcia,Perez,Torres,Torrez,Sanchez,Zanchez,Rubilar,Abelen,Cosme,Cornillo,Cara,Rubio,Lalala,Gonzalez,Rodriguez,Gomez,Lopez,Sosa,Alvarez,Diaz,Godoy,Villalva,Ponce,Vera,Navarro,Soto,Campos,Cola,Bravo,Valdez";
	private static String Ciencia="Lengua,Historia,Fisica,Matematica,Civica,Biologia,Geografia,Quimica,Analisis Matematico,Contabilidad,Musica,Plastica,Lengua y literatura,Dibujo,Educacion fisica";
	private static String Calle="Jujuy,La Rioja,Salta,Pampa,Misiones,Buenos Ahires,Bahia Blanca,Mendoza,Santafe,Boedo,San Martin,Belgrano,Aconcagua,Domene,Chile,España,Godoy,Perito Moreno";
	private static String Letras="A,B,C,D,E,F,G,H";
	
	public static String ObtenerNombre()
	{
		return ObtenerValor(Nombre);
	}
	
	public static String ObtenerApellido()
	{
		return ObtenerValor(Apellido);
	}
	
	public static String ObtenerCiencia()
	{
		return ObtenerValor(Ciencia);
	}
	
	public static String ObtenerCalle()
	{
		return ObtenerValor(Calle);
	}
	
	public static String ObtenerLetras()
	{
		return ObtenerValor(Letras);
	}
	
	public static int Random(int Inicial, int Final)
	{
		Random rnd = new Random();
		int Resul=(int) (rnd.nextDouble() * Final + Inicial);
		return Resul;
	}
	
	private static String ObtenerValor(String valor)
	{
		String[] Partes=Dividir(valor);
		return Partes[Random(0,Partes.length)];
	}
	
	private static String[] Dividir(String palabras)
	{
		String[] partes = palabras.split(",");
		return partes;
	}
	
	
}
