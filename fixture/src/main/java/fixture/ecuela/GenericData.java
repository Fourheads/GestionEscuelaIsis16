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

package fixture.ecuela;

import java.util.Random;

public class GenericData {
	
	private static int	Cantidad=20;
	
	private static String Nombre="Santiago,Sebastián,Matías,Nicolás,Samuel,Alejandro,Mateo,Diego,Benjamín,Daniel,Joaquín,Tomás,Gabriel,Lucas,Martín,Emmanuel,Alexander,David,Emiliano,Carlos,JuanJosé,Andrés,Felipe,Ignacio,Leonardo,Adrián,Francisco,Rodrigo,Alvaro,MiguelÁngel,Fernando,Santino,Bautista,Agustín,JuanPablo,Vicente,Thiago,Maximiliano,Pablo,Eduardo,Christopher,Kevin,Isaac,JuanDiego,Aarón,Dylan,Jesús,Esteban,Manuel,JuanSebastián,Franco,Lautaro,Miguel,JuanDavid,Ricardo,Bruno,Luciano,Juan,Emilio,JuanEsteban,Julián,Valentino,Javier,Joshua,Rafael,Jorge,José,Luis,DiegoAlejandro,Gael,Óscar,Nahuel,Máximo,Axel,Facundo,Jonathan,Ian,Josué,Camilo,Sergio,Jerónimo,Álex,Mauricio,JuanCamilo,Alonso,Anthony,Dante,Christian,Simón,Patricio,Héctor,Iván,Marcos,Ramiro,Alberto,Matthew,Pedro,Mario,Alan,Arturo,Adrian,Manuel,Jose,Gogo,Pedro,Mariano,Leandro,Gonzalo,Roberto,Daniel,Fernando,Damian,Oscar,Lautaro,Miguel,Diego,Pablo,Raul,Ricardo,Matias,Hector,Juan,Emiliano,Cesar,Gerardo,Emiliano,Francisco,Martin,Leonardo";
	private static String Apellido="Lala,González,Rodríguez,Lolo,Gómez,Fernández,López,Torroija,Díaz,Martínez,Pérez,García,Sánchez,Romero,Sosa,Álvarez,Torres,Ruiz,Ramírez,Flores,Acosta,Benítez,Medina,Suárez,Herrera,Aguirre,Pereyra,Gutiérrez,Giménez,Molina,Silva,Benigar,Castro,Rojas,Ortíz,Núñez,Luna,Juárez,Cabrera,Ríos,Ferreyra,Godoy,Morales,Domínguez,Moreno,Peralta,Vega,Carrizo,Quiroga,Garcia Pacek,Castillo,Ledesma,Muñoz,Ojeda,Ponce,Vera,Vázque,Villalba,Cardozo,Navarro,Ramos,Arias,Coronel,Córdoba,Figueroa,Correa,Cáceres,Vargas,Maldonado,Mansilla,Farías,Rivero,Paz,Miranda,Roldán,Méndez,Lucero,Cruz,Hernández,Agüero,Páez,Blanco,Mendoza,Barrios,Escobar,Ávila,Soria,Leiva,Acuña,Martin,Maidana,Moyano,Cola,Campos,Olivera,Duarte,Soto,Franco,Bravo,Valdéz,Toledo,Andrada,Andrade,Montenegro,Leguizamón,Chávez,Arce,Valdez,Yong Wong";
	private static String Materias="Lengua,Historia,Fisica,Matematica,Civica,Biologia,Geografia,Quimica,Analisis Matematico,Contabilidad,Musica,Plastica,Lengua y literatura,Dibujo,Educacion fisica,Taller";
	private static String Calle="Jujuy,La Rioja,Salta,Pampa,Misiones,Buenos Ahires,Bahia Blanca,Mendoza,Santafe,Boedo,San Martin,Belgrano,Aconcagua,Domene,Chile,España,Godoy,Perito Moreno";
	private static String Letras="A,B,C,D,E,F,G,H";
	private static String Plan="Bachillerato,Tecnico Maestro mayor de obra,Musica,Agricola,Artistica,Polimodal,Tecnico Industrial,Tecnico Auxiliar,Tecnico Mecanico,Tecnico Quimico,Tecnico Electromecanico,Bachillerato Perito Mercantil,Bachillerato Contable";
	
	public static int ObtenerCantidad()
	{
		return Cantidad;
	}
	
	
	public static String ObtenerPlan()
	{
		return ObtenerValor(Plan);
	}
	
	
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
		return ObtenerValor(Materias);
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
	
	public static String[] Dividir(String palabras)
	{
		String[] partes = palabras.split(",");
		return partes;
	}
	
	
}
