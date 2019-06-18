package eu.ensg.jpo.explor_descartes;

import java.security.MessageDigest;

public final class SHA {

    /**
     * Fonction encodant les mots de passe en SHA256
     *
     * @param mdp: le mot de passe à encoder
     * @return le mot de passe encodé
     */

    public static String encode(String mdp) throws Exception{

        /**
         *  Fonction encodant une chaine de caractère
         *
         * @param mdp : mot de passe que l'on cherche à encoder
         */

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        // Convertir le tableau de bits en une format hexadécimal :
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //System.out.println("En format hexa : " + sb.toString());
        return sb.toString();

    }
}
