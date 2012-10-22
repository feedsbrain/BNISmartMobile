package com.indragunawan.smartmobile.bni.common;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

import com.indragunawan.smartmobile.bni.R;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class DialogFactory {

	public static Builder createConfirmDialog(Context context, Integer menuCode, OnClickListener listener, String... args) {
		String message = "";

		if (menuCode.equals(R.string.saldo)) {
			message = context.getString(R.string.cek_saldo_confirm);
		}

		if (menuCode.equals(R.string.tagihan)) {
			message = context.getString(R.string.tagihan_confirm);
		}

		if (menuCode.equals(R.string.pembayaran)) {
			message = context.getString(R.string.pembayaran_confirm);
		}

		if (menuCode.equals(R.string.poin)) {
			message = context.getString(R.string.poin_confirm);
		}

		if (menuCode.equals(R.string.kenaikan_limit)) {
			message = context.getString(R.string.kenaikan_limit_confirm);
			message = message.replace(SyntaxTemplate.LIMIT_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[1].trim());
		}

		if (menuCode.equals(R.string.cetak_ulang)) {
			message = context.getString(R.string.cetak_ulang_confirm);
			message = message.replace(SyntaxTemplate.PERIODE_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.FAX_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.danaplus)) {
			message = context.getString(R.string.danaplus_confirm);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.telkom)) {
			message = context.getString(R.string.phone_bill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.TELKOM);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.telkomsel)) {
			message = context.getString(R.string.phone_bill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.TELKOMSEL);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.simpati)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.SIMPATI);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.as)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.AS);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.mentari)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.MENTARI);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.starone)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.STARONE);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.im3)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.IM3);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.fren)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.SMARTFREN);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.flexi)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.FLEXI);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.xl_jempol)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.JEMPOL);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.esia)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.ESIA);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.xl_reg)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.XL_BEBAS_REGULER);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.xl_xtra)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.XL_BEBAS_XTRA);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.three)) {
			message = context.getString(R.string.phone_reload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.THREE);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[2].trim());
		}

		if (menuCode.equals(R.string.bill_telkom)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.TELKOM);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.bill_telkomsel)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.TELKOMSEL);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.bill_matrix)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.MATRIX);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.bill_indovision)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.INDOVISION);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.bill_kabelvision)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.KABELVISION);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.bill_tpj)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.TPJ);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.bill_xplor)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.XPLOR);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.bill_fren)) {
			message = context.getString(R.string.phone_smartbill_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.SMARTFREN);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[3].trim());
		}

		if (menuCode.equals(R.string.auto_simpati)) {
			message = context.getString(R.string.phone_smartreload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.SIMPATI);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[3].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[4].trim());
		}

		if (menuCode.equals(R.string.auto_mentari)) {
			message = context.getString(R.string.phone_smartreload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.MENTARI);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[3].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[4].trim());
		}

		if (menuCode.equals(R.string.auto_im3)) {
			message = context.getString(R.string.phone_smartreload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.IM3);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[3].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[4].trim());
		}

		if (menuCode.equals(R.string.auto_fren)) {
			message = context.getString(R.string.phone_smartreload_confirm);
			message = message.replace(SyntaxTemplate.BILLING_TEMPLATE, Constants.SMARTFREN);
			message = message.replace(SyntaxTemplate.TELEPON_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[3].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[4].trim());
		}

		if (menuCode.equals(R.string.smart_transfer)) {
			message = context.getString(R.string.smart_transfer_confirm);
			message = message.replace(SyntaxTemplate.REK_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.BANK_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.BRANCH_TEMPLATE, args[3].trim());
			message = message.replace(SyntaxTemplate.AMOUNT_TEMPLATE, args[4].trim());
			message = message.replace(SyntaxTemplate.DATE_TEMPLATE, args[5].trim());
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[6].trim());
		}

		if (menuCode.equals(R.string.pin)) {
			message = context.getString(R.string.change_pin_confirm);
			message = message.replace(SyntaxTemplate.KARTU_TEMPLATE, args[0].trim());
		}

		if (menuCode.equals(R.string.fitur)) {
			message = context.getString(R.string.fitur_confirm);
		}

		if (menuCode.equals(R.string.register)) {
			message = context.getString(R.string.register_confirm);
		}

		if (menuCode.equals(R.string.aplikasi)) {
			message = context.getString(R.string.aplikasi_confirm);
			message = message.replace(SyntaxTemplate.NAME_TEMPLATE, args[0].trim());
			message = message.replace(SyntaxTemplate.ADDRESS_TEMPLATE, args[1].trim());
			message = message.replace(SyntaxTemplate.CITY_TEMPLATE, args[2].trim());
			message = message.replace(SyntaxTemplate.ZIPCODE_TEMPLATE, args[3].trim());
		}

		if (message.trim() != "") {
			return new AlertDialog.Builder(context).setTitle(context.getString(R.string.confirmation)).setMessage(message)
					.setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(R.string.yes_button, listener)
					.setNegativeButton(R.string.no_button, listener);
		} else {
			return null;
		}
	}

	public static Builder createWarningDialog(Context context, String message, OnClickListener listener) {
		if (message.trim() != "") {
			return new AlertDialog.Builder(context).setTitle(context.getString(R.string.attention)).setMessage(message)
					.setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(R.string.ok_button, listener);
		} else {
			return null;
		}
	}
}
