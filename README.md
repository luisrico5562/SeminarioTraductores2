# Seminario de Traductores II
Repositorio para la materia Seminario de Solución de Problemas de Traductores de Lenguajes II

Este proyecto consiste en la creación de un traductor, el cual consta de 3 partes principales, o analizadores: léxico, sintáctico y semántico.

El lenguaje de programación utilizado para la totalidad del proyecto es Java en NetBeans.

### NOTA: El repositorio está creado directamente desde NetBeans, por lo que todos los códigos se encuentran dentro de la carpeta "src".

## Analizador léxico
Para el analizador léxico la cadena de entrada primero pasa por 3 procesos para separar cada subcadena con el fin de poder ser analizada por el autómata. dichos procesos son los siguientes:

- Proceso 1: Separa las subcadenas según los espacios y saltos de línea ("a + b" :arrow_right: "a", "+", "b").
- Proceso 2: Separa según el tipo de caracter de la subcadena ("while(a)" :arrow_right: "while", "(", "a", ")").
- Proceso 3: Separa las cadenas de símbolos (";;" :arrow_right: ";", ";").

### [Mini analizador léxico](https://github.com/luisrico5562/SeminarioTraductores2/commit/b7a229a1b9ca0b75ed926110bc2f6af2ef4e858c)
Antes de la creación del analizador léxico completo, primero se crea una versión que solo permite detectar identificadores y números reales, el cual está hecho a partir del siguiente autómata:
![Mini generador léxico](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/4a8a4844-0b08-40ab-bf44-b51e194e07ee)

#### El resultado es el siguiente:
![java_mzzmIUETKP](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/8abdc884-963d-4eeb-bfaf-196c5dc737e4)

[Ver mini analizador léxico](https://github.com/luisrico5562/SeminarioTraductores2/commit/b7a229a1b9ca0b75ed926110bc2f6af2ef4e858c)

### [Analizador léxico final](https://github.com/luisrico5562/SeminarioTraductores2)
Este ya tiene la capacidad de analizar todas las palabras indicadas por el profesor:
- Palabras reservadas: if, while, return, else, int, float
- Identificadores: letra(letra|dígito)*
- Entero: dígito+
- Real: entero.entero
- Operador de adición: +, -
- Operador de multiplicación: *, /
- Operador de asignación: =
- Operador relacional: <, >, <=, >=, !=, ==
- Operador And: &&
- Operador Or: ||
- Operador Not: !
- Parentesis: (, )
- Llave: {, }
- Punto y coma: ;
El autómata creado para el analizador se dividió en 3 partes para su mejor comprensión:
#### Palabras reservadas e identificadores
![Generador léxico 1](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/414191a0-dc1e-47ae-8e9e-7342414d808f)
NOTA 1: todos los estados pueden llevar a S25 si se introduce una letra o número diferente al de su estado siguiente.

NOTA 2: si el flojo llega al final de una palabra reservada y la cadena aún tiene caracteres, se revisa si el siguiente caracter es una letra; en cuyo caso se manda el flujo a S25.


#### Enteros y reales
![Generador léxico 2](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/e8fb9884-92a9-48f2-9cc1-2aac97a79d4f)


#### Operadores, paréntesis, llaves y punto y coma
![Generador léxico 3](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/20183103-daa3-4009-bb31-620f68484e4e)
NOTA : para S31, S32 Y S33 se revisa si la cadena tiene más caracteres, para después comprobar si el siguiente es un '='; en cuyo caso se avanza a S34.


#### El resultado del analizador léxico es el siguiente:
![java_j4Px3TYKmk](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/1c00af0e-eb70-4ee6-94e9-274a3c5ece3b)


[Ver analizador léxico](https://github.com/luisrico5562/SeminarioTraductores2)

## Analizador sintáctico
### [Mini analizador sintáctico - Ejercicio 1](https://github.com/luisrico5562/SeminarioTraductores2/tree/Branch-Mini-Analizador-Sintactico-Ejercicio1)
En este ejercicio se crea un analizador sintáctico con la siguiente regla:

R1: E -> id + id

#### El resultado es el siguiente:
![java_lBaKwoHmUK](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/9b5f2d94-551f-4dc6-be6e-9789c8ed886b)


[Ver mini analizador sintáctico - Ejercicio 1 (branch)](https://github.com/luisrico5562/SeminarioTraductores2/tree/Branch-Mini-Analizador-Sintactico-Ejercicio1)


### [Mini analizador sintáctico - Ejercicio 2](https://github.com/luisrico5562/SeminarioTraductores2/tree/Branch-Mini-Analizador-Sintactico-Ejercicio2)
En este ejercicio se crea un analizador sintáctico con las siguientes reglas:

R1: E -> id + E

R2: E -> id

#### El resultado es el siguiente:
![java_clxUV8Gmzl](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/5078249d-8b39-4f0a-96e5-f7678a556bed)


[Ver mini analizador sintáctico - Ejercicio 2 (branch)](https://github.com/luisrico5562/SeminarioTraductores2/tree/Branch-Mini-Analizador-Sintactico-Ejercicio2)

### Analizador sintáctico final
Se agrega la gramática propuesta por el profesor.
#### El resultado es el siguiente:
![java_dVTshBK3yz](https://github.com/luisrico5562/SeminarioTraductores2/assets/127691671/e1af025a-3695-46f6-b7b7-1f75eedbad01)
