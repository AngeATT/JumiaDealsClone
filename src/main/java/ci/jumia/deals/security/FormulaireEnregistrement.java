package ci.jumia.deals.security;

import java.util.Map;
import lombok.Data;

@Data
public class FormulaireEnregistrement {
  String nom;
  String email;
  String numero;
  Map<String, Boolean> numeros;
}
