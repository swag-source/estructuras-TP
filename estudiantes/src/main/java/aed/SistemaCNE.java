package aed;
public class SistemaCNE {
    // Completar atributos privados
    private String[] _nombresPartidos;
    private String[] _nombresDistritos;
    private int[] _diputadosPorDistrito;
    private int [] _ultimasMesasDistritos;



    public class VotosPartido{
        private int presidente;
        private int diputados;
        VotosPartido(int presidente, int diputados){this.presidente = presidente; this.diputados = diputados;}
        public int votosPresidente(){return presidente;}
        public int votosDiputados(){return diputados;}
    }

    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos, int[] ultimasMesasDistritos) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (nombresPartidos[i]!= null){
            _nombresPartidos[i]=nombresPartidos[i];
            i ++;
        }
        while (nombresDistritos[j]!= null){
            _diputadosPorDistrito[j]= diputadosPorDistrito[j];
            _nombresDistritos[j]=nombresDistritos[j];
            _ultimasMesasDistritos[j] = ultimasMesasDistritos[j];
            j ++;
        }
     }


    public String nombrePartido(int idPartido) {
        return _nombresPartidos[idPartido];
    }

    public String nombreDistrito(int idDistrito) {
        return _nombresDistritos[idDistrito];
    }

    public int diputadosEnDisputa(int idDistrito) {
        return _diputadosPorDistrito[idDistrito] ;
    }

    public String distritoDeMesa(int idMesa) {
        while (_ultimasMesasDistritos[k] != 0){
            if(k == 0 && 0 <= idMesa < _ultimasMesasDistritos[k]){
                return _nombresDistritos[k];
            }else{
                if((_ultimasMesasDistritos[k-1]<=idMesa) && (idMesa < _ultimasMesasDistritos[k])){
                    return _nombresDistritos[k];
                }else{
                    i++;
                }
            }
        };
    }

    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int votosPresidenciales(int idPartido) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int[] resultadosDiputados(int idDistrito){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public boolean hayBallotage(){
        throw new UnsupportedOperationException("No implementada aun");
    }
}

