package aed;

public class SistemaCNE {
    private String[] _nombresPartidos;
    // [str, str, ...] <- indice = id.partido
    private String[] _nombresDistritos;
    // [str, str, ...] <- indice = id.partido
    private int[] _diputadosPorDistrito;
    // [int, int, ...] <- indice = id.distrito
    private int[] _ultimasMesasDistritos;
    // [int, int, ...] <- indice = id.distrito (rango mesas) cada posicion tiene (int - 1) mesas
    private int[] votos_presidente;
    // los votos presidenciales tiene por idPartido la #votos presidenciales
    private int[][] votos_diputados;
    // los votos diputados tiene por indice de matriz el distrito y por cada distrito tomamos el idPartido asignando #votosDiputado
    private int[] registroMesa;
    public class VotosPartido{
        private int presidente;
        private int diputados;
        VotosPartido(int presidente, int diputados){
            this.presidente = presidente;
            this.diputados = diputados;
        }
        public int votosPresidente(){
            return presidente;
        }
        public int votosDiputados(){
            return diputados;
        }
    }
    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        // inicializamos los atributos privados
        _nombresPartidos = new String[nombresPartidos.length];
        _diputadosPorDistrito = new int[nombresDistritos.length];
        _nombresDistritos = new String[nombresDistritos.length];
        _ultimasMesasDistritos = new int[nombresDistritos.length];
        votos_presidente = new int[nombresPartidos.length];
        votos_diputados = new int[nombresDistritos.length][nombresPartidos.length];
        registroMesa = new int[ultimasMesasDistritos[ultimasMesasDistritos.length-1]];

        int i = 0;
        int j = 0;

        // observamos que en el peor de los casos mi primer while itera hasta |nombresPartidos| = P => O(P)
        while (i < nombresPartidos.length){
            _nombresPartidos[i]=nombresPartidos[i];
            i ++;
        }

        // observamos que en el peor de los casos mi segundo while itera hasta nombresDistritos = D => O(D)
        while (j < nombresDistritos.length){
            _diputadosPorDistrito[j]= diputadosPorDistrito[j];
            _nombresDistritos[j]=nombresDistritos[j];
            _ultimasMesasDistritos[j] = ultimasMesasDistritos[j];
            j ++;
        }

        // al finalizar el algoritmo tenemos que ejecutando ambos whiles, la complejidad nos queda:
        // O(P) + O(D) ∈ O(P*D)
     }


    public String nombrePartido(int idPartido) {
        // O(1) porque obtener sobre arreglos => O(1)
        return _nombresPartidos[idPartido];
    }

    public String nombreDistrito(int idDistrito) {
        // O(1) porque obtener sobre arreglos => O(1)
        return _nombresDistritos[idDistrito];
    }

    public int diputadosEnDisputa(int idDistrito) {
        // O(1) porque obtener sobre arreglos => O(1)
        return _diputadosPorDistrito[idDistrito] ;
    }

    public String distritoDeMesa(int idMesa) {
        // Agarro ultimasMesasDistritos. Pregunto si idMesa = ultimasMesasDistritos[medio] => medio =  div(i,2)
        int izquierda = 0;
        int derecha = this._ultimasMesasDistritos.length - 1;
        int medio = derecha / 2;

        if (idMesa < _ultimasMesasDistritos[0]) {
            return _nombresDistritos[0];
        } else {
            while (derecha - izquierda > 1) {
                if (idMesa < _ultimasMesasDistritos[medio]){
                    derecha = medio;
                    // vemos que las divisoines por 2 hacen que la complejidad sea logaritmica respecto a n
                    medio = (derecha - izquierda) / 2;
                } else {
                    izquierda = medio;
                    medio = (derecha + izquierda) / 2;
                }
            } // => mi while tiene complejidad O(log(n))

            if (idMesa >= _ultimasMesasDistritos[izquierda]){
                return _nombresDistritos[izquierda + 1];
            } else {
                return _nombresDistritos[izquierda];
            }
        }
    }
    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        // a nosotros nos ingresa una lista con los votos por partido en la que asumimos que estan en orden por idPartido
        // por requiere no tenemos que chequear si idMesa fue registrado

        //primero hacemos la busqueda binaria en la mesa para saber en que distrito tenemos que pararnos.
        int izquierda = 0;
        int derecha = this._ultimasMesasDistritos.length - 1;
        int medio = derecha / 2;
        int res = 0;

        if(idMesa >= _ultimasMesasDistritos[0]) {
            while (derecha - izquierda > 1) {
                if (idMesa < _ultimasMesasDistritos[medio]){
                    derecha = medio;
                    // vemos que las divisoines por 2 hacen que la complejidad sea logaritmica respecto a n
                    medio = (derecha - izquierda) / 2;
                } else {
                    izquierda = medio;
                    medio = (derecha + izquierda) / 2;
                }
            } // => mi while tiene complejidad O(log(n))

            if (idMesa >= _ultimasMesasDistritos[izquierda]){
                res =  izquierda + 1;
            } else {
                res = izquierda;
            }
        }
        //despues tenemos que rellenar en el indice que ya sabemos del distrito con el while respectivamente
        int i = 0;
        while (i < actaMesa.length) {
            this.votos_presidente[i] += actaMesa[i].presidente;
            this.votos_diputados[res][i] += actaMesa[i].diputados;
            i++;
        } // O(M)
        this.registroMesa[res] = idMesa;
        // O(log(n)) + O(M) = O(M + log(n))
    }


    public int votosPresidenciales(int idPartido) {
        return votos_presidente[idPartido];
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        return votos_diputados[idDistrito][idPartido];
    }

    public int[] resultadosDiputados(int idDistrito){
    // nos pide la cantidad de bancas del partido i en el distrito iddistrito
        int _cantBancas = _diputadosPorDistrito[idDistrito];
        int i = 0;
        int[] votos_partido = new int[_diputadosPorDistrito.length];
        Heap heap = new Heap(_diputadosPorDistrito.length);
        int j = 1;
        int[] bancas_partido = new int[_nombresPartidos.length];

        while(i < _diputadosPorDistrito.length){
            // me armo la lista con los votos x partido
            votos_partido[i] = votosDiputados(i,idDistrito);
            // me armo el heap con los elementos de mi lista y su indice (son nodos)
            heap.insertar(votos_partido[i], i);
            i++;
        }
        while (j <= _cantBancas){
            aed.Heap.Nodo max = heap.obtenerMaximo();
//            heap.eliminar(max.votos, max.indice);
            bancas_partido[max.indice] ++;
            heap.insertar(max.votos / bancas_partido[max.indice] + 1, max.indice);
            heap.maxHeapify(max.indice);
            _cantBancas--;
        }
        return bancas_partido;
    }

    public boolean hayBallotage(){
        throw new UnsupportedOperationException("No implementada aun");
    }
}
