package com.fahrul.rml_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferResponse {
	 private String status;
	    private String message;
	    private Double jumlahTransfer;
	    private String rekeningPengirim;
	    private String rekeningPenerima;
	    private Double saldoPengirim;
	    private Double saldoPenerima;
}
