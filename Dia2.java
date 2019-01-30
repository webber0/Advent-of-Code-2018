package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Dia2 {

	public static void main(String[] args) {
		Dia2 ejercicio=new Dia2();
		//ejercicio.primera_parte("day2.in"); //O(n)
		ejercicio.segunda_parte("day2.in"); //O(n^2)

	}
	/*
	 * --- Day 2: Inventory Management System ---
	You stop falling through time, catch your breath, and check the screen on the device. "Destination reached. Current Year: 1518. Current Location: North Pole Utility Closet 83N10." You made it! Now, to find those anomalies.

	Outside the utility closet, you hear footsteps and a voice. "...I'm not sure either. But now that so many people have chimneys, maybe he could sneak in that way?" Another voice responds, "Actually, we've been working on a new kind of suit that would let him fit through tight spaces like that. But, I heard that a few days ago, they lost the prototype fabric, the design plans, everything! Nobody on the team can even seem to remember important details of the project!"

	"Wouldn't they have had enough fabric to fill several boxes in the warehouse? They'd be stored together, so the box IDs should be similar. Too bad it would take forever to search the warehouse for two similar box IDs..." They walk too far away to hear any more.

	Late at night, you sneak to the warehouse - who knows what kinds of paradoxes you could cause if you were discovered - and use your fancy wrist device to quickly scan every box and produce a list of the likely candidates (your puzzle input).

	To make sure you didn't miss any, you scan the likely candidate boxes again, counting the number that have an ID containing exactly two of any letter and then separately counting those with exactly three of any letter. You can multiply those two counts together to get a rudimentary checksum and compare it to what your device predicts.

	For example, if you see the following box IDs:

	abcdef contains no letters that appear exactly two or three times.
	bababc contains two a and three b, so it counts for both.
	abbcde contains two b, but no letter appears exactly three times.
	abcccd contains three c, but no letter appears exactly two times.
	aabcdd contains two a and two d, but it only counts once.
	abcdee contains two e.
	ababab contains three a and three b, but it only counts once.
	Of these box IDs, four of them contain a letter which appears exactly twice, and three of them contain a letter which appears exactly three times. Multiplying these together produces a checksum of 4 * 3 = 12.

	What is the checksum for your list of box IDs?
	 */
	
	/*
	 * The strategy i've taken is the following: I've a defined array of 26 elements (quantity of letters)
	 * each position represent a letter (a=0, b=1,c=2,...), Then, i count how many times appears a defined letter
	 * and add 1. Then i count how many 2 and 3 i've got inside the array. IF appears twice, i only count the first
	 * and rise a flag (since i don't care which letter appeared first).
	 */
	public void primera_parte(String path) {
		int aparece_dos_veces=0,aparece_tres_veces=0;
		int[] abecedario=new int[26];
		String linea;
		File archivo=new File(path);
		try {
			Scanner sc=new Scanner(archivo);
			while(sc.hasNextLine()) {
				inicializar_vector(abecedario);
				linea=sc.nextLine();
				procesar_linea(abecedario,linea);
				for(int j=0;j<abecedario.length;j++) {
					if(abecedario[j]==2) {
						aparece_dos_veces++;
						break;
					}
				}
				for(int j=0;j<abecedario.length;j++) {
					if(abecedario[j]==3) {
						aparece_tres_veces++;
						break;
					}
				}
				
			}
			sc.close();
			int resultado=(aparece_dos_veces*aparece_tres_veces);
			System.out.println("Our Checksum is: "+resultado);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void procesar_linea(int[] abecedario, String linea) {
		char caracter_actual;
		for(int i=0;i<linea.length();i++) {
			caracter_actual=linea.charAt(i);
			switch(caracter_actual) {
			case 'a': abecedario[0]++; break;
			case 'b': abecedario[1]++; break;
			case 'c': abecedario[2]++; break;
			case 'd': abecedario[3]++; break;
			case 'e': abecedario[4]++; break;
			case 'f': abecedario[5]++; break;
			case 'g': abecedario[6]++; break;
			case 'h': abecedario[7]++; break;
			case 'i': abecedario[8]++; break;
			case 'j': abecedario[9]++; break;
			case 'k': abecedario[10]++; break;
			case 'l': abecedario[11]++; break;
			case 'm': abecedario[12]++; break;
			case 'n': abecedario[13]++; break;
			case 'o': abecedario[14]++; break;
			case 'p': abecedario[15]++; break;
			case 'q': abecedario[16]++; break;
			case 'r': abecedario[17]++; break;
			case 's': abecedario[18]++; break;
			case 't': abecedario[19]++; break;
			case 'u': abecedario[20]++; break;
			case 'v': abecedario[21]++; break;
			case 'w': abecedario[22]++; break;
			case 'x': abecedario[23]++; break;
			case 'y': abecedario[24]++; break;
			case 'z': abecedario[25]++; break;
			}
		}
		
	}
	private void inicializar_vector(int[] abecedario) {
		for(int i=0;i<abecedario.length;i++) {
			abecedario[i]=0;
		}
	}
	
	/*
	 * --- Part Two ---
Confident that your list of box IDs is complete, you're ready to find the boxes full of prototype fabric.

The boxes will have IDs which differ by exactly one character at the same position in both strings. For example, given the following box IDs:

abcde
fghij
klmno
pqrst
fguij
axcye
wvxyz
The IDs abcde and axcye are close, but they differ by two characters (the second and fourth). However, the IDs fghij and fguij differ by exactly one character, the third (h and u). Those must be the correct boxes.

What letters are common between the two correct box IDs? (In the example above, this is found by removing the differing character from either ID, producing fgij.)
	 */
	/*
	 * So far, the "best" solution i've reached for this case is, i all the input in memory (since i've to check each
	 * line against all the file) and, with a ArrayList (which grows on demand) i try to find the 2 Box IDs that differs
	 * in exactly 1 character...
	 */
	public void segunda_parte(String path) {
		ArrayList<String>palabras=new ArrayList<String>();
		int i=0,j=0;
		boolean flag_to_stop=false;
		String first_string="",second_string="";
		File archivo=new File(path);
		try {
			Scanner sc=new Scanner(archivo);
			while(sc.hasNext()) {
				palabras.add(sc.nextLine());
			}
			sc.close();  //when i've all the strings on memory, i dont really need the file anymore
			for(i=0;i<palabras.size();i++) {
				for(j=0;j<palabras.size();j++) {
					if(i!=j) { //to avoid compare the same word
						if(comparador_de_strings(palabras.get(i), palabras.get(j))==1) {
							first_string=palabras.get(i);
							second_string=palabras.get(j);
							flag_to_stop=true;
							break;
						}
					}else;
				} if(flag_to_stop) {
					eliminar_letra_extra(first_string, second_string);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private int comparador_de_strings(String first_string,String second_string) {
		int f=0,cantidad_de_letras_diferentes=0;
		for(f=0;f<first_string.length();f++) {
			if(first_string.charAt(f)!=second_string.charAt(f)) cantidad_de_letras_diferentes++;
		}
		return cantidad_de_letras_diferentes;
	}
	
	private void eliminar_letra_extra(String first_string,String second_string) {
		String resultado="";
		int f=0,g=0;
		for(g=0;g<first_string.length();g++) {
			if(first_string.charAt(g)==second_string.charAt(g)) {
				resultado+=first_string.charAt(g);
			}
		}System.out.println(resultado);
	}
}
