package com.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.X19Detail;
import com.example.webapp.entity.X19Header;
import com.example.webapp.form.JuchuDetailForm;
import com.example.webapp.form.JuchuForm;
import com.example.webapp.repository.X19HeaderRepository;

@Service
public class JuchuService {

    @Autowired
    private X19HeaderRepository x19HeaderRepository;

    @Transactional
    public void save(JuchuForm form) {
        X19Header header = new X19Header();

        header.setDenpyoNo(form.getDenpyoNo());

        header.setJuchuDate(form.getJuchuDate());
        header.setSeizoDate(form.getSeizoDate());
        header.setNouhinDate(form.getNouhinDate());
        header.setTokuiKensaku(form.getTokuiKensaku());
        header.setTokuiNo(form.getTokuiNo());
        header.setTokuiName(form.getTokuiName());
        header.setBumonNo(form.getBumonNo());
        header.setBumonName(form.getBumonName());
        header.setTenpoNo(form.getTenpoNo());
        header.setTenpoName(form.getTenpoName());
        header.setTantoNo(form.getTantoNo());
        header.setTantoName(form.getTantoName());
        header.setTekiyo(form.getTekiyo());

        if (form.getDetails() != null) {
            for (JuchuDetailForm dForm : form.getDetails()) {
                // 品番が選択・入力されている行のみ保存対象とする
                if (dForm.getHinban() != null && !dForm.getHinban().isEmpty()) {
                    X19Detail detail = new X19Detail();
                    detail.setHinbanKensaku(dForm.getHinbanKensaku());
                    detail.setHinban(dForm.getHinban());
                    detail.setHinban2(dForm.getHinban2());
                    detail.setHinban3(dForm.getHinban3());
                    detail.setHinmei(dForm.getHinmei());
                    detail.setKikaku(dForm.getKikaku());
                    detail.setHacchuTani(dForm.getHacchuTani());
                    detail.setQuantity(dForm.getQuantity());
                    detail.setUnitName(dForm.getUnitName());
                    detail.setUnitPrice(dForm.getUnitPrice());
                    detail.setAmount(dForm.getAmount());

                    header.addDetail(detail);
                }
            }
        }

        x19HeaderRepository.save(header);
    }
}
