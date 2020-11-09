package com.letscode.java.springbank.mensagem;

import com.letscode.java.springbank.cliente.Cliente;
import com.letscode.java.springbank.gerente.Gerente;
import com.letscode.java.springbank.produto.Produto;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class MensagemDireta {
    
    private static final String MALA_DIRETA = "Prezado(a), gostariamos de ofertar a adesão ao produto %s";
    
    private final Gerente remetente;
    private final List<Cliente> destinatarios;
    private final String corpoMensagem;
    private Boolean naoLida;
    
    public static MensagemDireta enviarMalaDireta(Gerente remetente, List<Cliente> destinatarios,
        Produto produto) {
        final String corpoMensagem = String.format(MensagemDireta.MALA_DIRETA, produto.toString());
        return new MensagemDireta(remetente, destinatarios, corpoMensagem);
    }
    
    public MensagemDireta(Gerente remetente, List<Cliente> destinatarios, String corpoMensagem) {
        this.remetente = remetente;
        this.destinatarios = destinatarios;
        this.corpoMensagem = corpoMensagem;
    }
    
    public List<Cliente> getDestinatarios() {
        return Collections.unmodifiableList(this.destinatarios);
    }
    
    public void marcarComoLida() {
        this.naoLida = false;
    }
}
