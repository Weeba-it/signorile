public class ListaConcatenata<T> {

    private class Nodo {
        T dato;
        Nodo next;

        Nodo(T dato) {
            this.dato = dato;
            this.next = null;
        }
    }

    private Nodo head;
    private int lunghezza;

    public ListaConcatenata() {
        this.head = null;
        this.lunghezza = 0;
    }

    public void aggiungiInTesta(T dato) {
        Nodo nuovo = new Nodo(dato);
        nuovo.next = head;
        head = nuovo;
        lunghezza++;
    }

    public void aggiungiInCoda(T dato) {
        Nodo nuovo = new Nodo(dato);
        if (head == null) {
            head = nuovo;
        } else {
            Nodo corrente = head;
            while (corrente.next != null) {
                corrente = corrente.next;
            }
            corrente.next = nuovo;
        }
        lunghezza++;
    }

    public void aggiungiInPosizione(T dato, int posizione) {
        if (posizione < 0 || posizione > lunghezza) {
            throw new IndexOutOfBoundsException("Posizione non valida: " + posizione);
        }
        if (posizione == 0) {
            aggiungiInTesta(dato);
            return;
        }
        Nodo nuovo = new Nodo(dato);
        Nodo prec = head;
        for (int i = 0; i < posizione - 1; i++) {
            prec = prec.next;
        }
        nuovo.next = prec.next;
        prec.next = nuovo;
        lunghezza++;
    }

    public boolean cancella(T dato) {
        if (head == null) return false;
        if (head.dato.equals(dato)) {
            head = head.next;
            lunghezza--;
            return true;
        }
        Nodo prec = head;
        Nodo corr = head.next;
        while (corr != null) {
            if (corr.dato.equals(dato)) {
                prec.next = corr.next;
                lunghezza--;
                return true;
            }
            prec = corr;
            corr = corr.next;
        }
        return false;
    }

    public T cancellaInPosizione(int posizione) {
        if (posizione < 0 || posizione >= lunghezza) {
            throw new IndexOutOfBoundsException("Posizione non valida: " + posizione);
        }
        if (posizione == 0) {
            T valore = head.dato;
            head = head.next;
            lunghezza--;
            return valore;
        }
        Nodo prec = head;
        for (int i = 0; i < posizione - 1; i++) {
            prec = prec.next;
        }
        T valore = prec.next.dato;
        prec.next = prec.next.next;
        lunghezza--;
        return valore;
    }

    public boolean contiene(T dato) {
        Nodo corrente = head;
        while (corrente != null) {
            if (corrente.dato.equals(dato)) {
                return true;
            }
            corrente = corrente.next;
        }
        return false;
    }

    public int indiceDi(T dato) {
        Nodo corrente = head;
        int pos = 0;
        while (corrente != null) {
            if (corrente.dato.equals(dato)) {
                return pos;
            }
            corrente = corrente.next;
            pos++;
        }
        return -1;
    }

    public T leggiTesta() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vuota");
        }
        return head.dato;
    }

    public T leggiCoda() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vuota");
        }
        Nodo corrente = head;
        while (corrente.next != null) {
            corrente = corrente.next;
        }
        return corrente.dato;
    }

    public T leggiInPosizione(int posizione) {
        if (posizione < 0 || posizione >= lunghezza) {
            throw new IndexOutOfBoundsException("Posizione non valida: " + posizione);
        }
        Nodo corrente = head;
        for (int i = 0; i < posizione; i++) {
            corrente = corrente.next;
        }
        return corrente.dato;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return lunghezza;
    }

    public void concatena(ListaConcatenata<T> altra) {
        if (altra == null || altra.isEmpty()) {
            return;
        }
        if (this.isEmpty()) {
            this.head = altra.head;
        } else {
            Nodo ultimo = head;
            while (ultimo.next != null) {
                ultimo = ultimo.next;
            }
            ultimo.next = altra.head;
        }
        this.lunghezza += altra.lunghezza;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Nodo corrente = head;
        while (corrente != null) {
            sb.append(corrente.dato);
            if (corrente.next != null) {
                sb.append(" → ");
            }
            corrente = corrente.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
