    // ============================================================================
    //                    EJERCICIO 1: obtenerTemperatura
    // ============================================================================
    /**
     * Obtiene la temperatura de una estación específica.
     * @param estacion Nombre de la estación a consultar
     * @param estaciones Array con los nombres de las estaciones
     * @param temperaturas Array de temperaturas
     * @return Temperatura de la estación, o -999 si no existe
     */
    static int obtenerTemperatura(String estacion, String[] estaciones, int[] temperaturas) {
        int posicion = buscar(estacion, estaciones);
        if (posicion != -1) {
            return temperaturas[posicion];
        }
        return -999;
    }

    // ============================================================================
    //                         EJERCICIO 2: lasActivas
    // ============================================================================
    /**
     * Devuelve un array con las estaciones activas y sus temperaturas.
     * @param temperaturas Array de temperaturas (-999 = fuera de servicio)
     * @param estaciones Array con los nombres de las estaciones
     * @return Array de Strings con formato "ESTACION temperatura"
     */
    static String[] lasActivas(int[] temperaturas, String[] estaciones) {
        // Primero contamos cuántas estaciones están activas
        int contadorActivas = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != -999) {
                contadorActivas++;
            }
        }
        
        // Creamos el array resultado con el tamaño exacto
        String[] resultado = new String[contadorActivas];
        
        // Llenamos el array con las estaciones activas
        int indice = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != -999) {
                resultado[indice] = estaciones[i] + " " + temperaturas[i];
                indice++;
            }
        }
        
        return resultado;
    }

    // ============================================================================
    //                         EJERCICIO 3: actualizar
    // ============================================================================
    /**
     * Actualiza la temperatura de una estación específica.
     * @param temperaturas Array de temperaturas a modificar
     * @param estaciones Array con los nombres de las estaciones
     * @param estacion Nombre de la estación a actualizar
     * @param nuevaTemp Nueva temperatura a registrar
     * @return true si se actualizó correctamente, false si la estación no existe
     */
    static boolean actualizar(int[] temperaturas, String[] estaciones, 
                              String estacion, int nuevaTemp) {
        int posicion = buscar(estacion, estaciones);
        if (posicion != -1) {
            temperaturas[posicion] = nuevaTemp;
            return true;
        }
        return false;
    }

    // ============================================================================
    //                      EJERCICIO 4: lasActivasOrden
    // ============================================================================
    /**
     * Devuelve las estaciones activas ordenadas por temperatura (decreciente).
     * @param temperaturas Array de temperaturas (-999 = fuera de servicio)
     * @param estaciones Array con los nombres de las estaciones
     * @return Array de Strings ordenado de mayor a menor temperatura
     */
    static String[] lasActivasOrden(int[] temperaturas, String[] estaciones) {
        // Primero contamos cuántas estaciones están activas
        int contadorActivas = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != -999) {
                contadorActivas++;
            }
        }
        
        // Creamos arrays temporales solo con las estaciones activas
        int[] tempActivas = new int[contadorActivas];
        String[] estActivas = new String[contadorActivas];
        
        int indice = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] != -999) {
                tempActivas[indice] = temperaturas[i];
                estActivas[indice] = estaciones[i];
                indice++;
            }
        }
        
        // Creamos el array resultado
        String[] resultado = new String[contadorActivas];
        
        // Ordenamos de mayor a menor usando selección
        for (int i = 0; i < contadorActivas; i++) {
            // Encontrar la posición de la mayor temperatura
            int posMax = posMayorTemp(tempActivas);
            
            // Añadir al resultado
            resultado[i] = estActivas[posMax] + " " + tempActivas[posMax];
            
            // Marcar como procesado (usamos -1 para que no se vuelva a elegir)
            tempActivas[posMax] = -1;
        }
        
        return resultado;
    }

// ============================================================================
    //          EJERCICIO 5: sinInterferenciasManhattan
    // ============================================================================
    /**
     * Verifica si las estaciones en el mapa tienen suficiente separación
     * usando distancia Manhattan (sin raíces cuadradas).
     * @param mapa Cuadrícula con las posiciones de las estaciones
     * @param distanciaMinima Distancia mínima requerida entre estaciones
     * @return true si no hay interferencias, false en caso contrario
     */
    static boolean sinInterferenciasManhattan(char[][] mapa, int distanciaMinima) {
        // Array para almacenar las coordenadas de las estaciones encontradas
        // Máximo 3 estaciones, cada una con 2 coordenadas (fila, columna)
        int[][] posiciones = new int[3][2];
        int contadorEstaciones = 0;
        
        // Buscar las estaciones en el mapa
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == 'N' || mapa[i][j] == 'S' || mapa[i][j] == 'C') {
                    posiciones[contadorEstaciones][0] = i; // fila (coordenada x)
                    posiciones[contadorEstaciones][1] = j; // columna (coordenada y)
                    contadorEstaciones++;
                }
            }
        }
        
        // Si hay 0 o 1 estación, no hay interferencias
        if (contadorEstaciones <= 1) {
            return true;
        }
        
        // Verificar la distancia entre todos los pares de estaciones
        for (int i = 0; i < contadorEstaciones; i++) {
            for (int j = i + 1; j < contadorEstaciones; j++) {
                // Calcular diferencias absolutas (valor absoluto manual)
                int dx = posiciones[i][0] - posiciones[j][0];
                if (dx < 0) {
                    dx = -dx;
                }
                int dy = posiciones[i][1] - posiciones[j][1];
                if (dy < 0) {
                    dy = -dy;
                }
                
                // Calcular distancia Manhattan
                int distancia = dx + dy;
                
                // Si alguna distancia es menor que la mínima, hay interferencias
                if (distancia < distanciaMinima) {
                    return false;
                }
            }
        }
        
        // Todas las distancias son suficientes
        return true;
    }
