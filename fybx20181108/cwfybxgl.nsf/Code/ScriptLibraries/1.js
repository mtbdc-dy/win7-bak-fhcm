function checkSubFormValidCustom(strAction) {

    if (dojo.byId("fldiseditable_xm").value == "yes") {
    	if (strAction == "save") {
            var strqcr;
            strqcr = dojo.byId("fldNiGaoRen").value;
            if (strqcr.indexOf("/") >= 0) {
                strqcr = strqcr.substring(0, strqcr.indexOf("/"));
            }
            //校验动态表格是否保存
            if (dijit.byId("grid_1")) {
                var grid = dijit.byId("grid_1");
                //alert(grid.editIndex);
                if (grid.editIndex != null) {
                    alert("请先保存动态行内容！");
                    return false;
                }

            }
            var bxmc = dojo.byId("fldbaoxxm_xm").value;
            if (dojo.byId("curflownum_forJS").value == "0") {
                dojo.byId("fldSubject").value = strqcr + "的" + bxmc + "报销申请(报销金额：" + dojo.byId("fldBaoxiaoje_xm").value + ")"
            }
            return true;
        }
        if (!indiValidate.validateAll()) {
            return false
        }

        var sfmjzj;
        if (dojo.byId("fldShifmjzj_xm")) {
            dojo.forEach(dojo.query("input[name='fldShifmjzj_xm']"),
            function(e, i) {
                if (e.checked == true) {
                    sfmjzj = e.value;
                }
            })
        }
        if (strAction == "submit") {

            if (dojo.byId("curflownum_forJS").value == "0") {
                var strqcr;
                strqcr = dojo.byId("fldNiGaoRen").value;
                if (strqcr.indexOf("/") >= 0) {
                    strqcr = strqcr.substring(0, strqcr.indexOf("/"));
                }
                var bxmc = dojo.byId("fldbaoxxm_xm").value;
                dojo.byId("fldSubject").value = strqcr + "的" + bxmc + "报销申请(报销金额：" + dojo.byId("fldBaoxiaoje_xm").value + ")"
                //校验附件
                var fjcount = dojo.byId("fujianpanel").getAttribute("count");
                if (fjcount == "0") {
                    alert("请您上传附件后提交！"); return false;
                }
                //需要校验关联文档，关联固定资产出库，入库单
                if (bxmc == "办公用品费") {
                    if (dojo.byId("fldifhavebgypk_xm").value == "是") {
                        var glwdcount = dojo.byId("guanlianpanel").getAttribute("count");
                        if (glwdcount == "0") {
                            alert("请您关联办公用品出库、入库申请单后提交！"); return false;
                        }
                    }
                }
                //需要校验关联文档，如果有出差申请库，需要强制关联出差
                if (bxmc == "差旅费") {
                    if (dojo.byId("fldifhaveccsq_xm").value == "是") {
                        var glwdcount = dojo.byId("guanlianpanel").getAttribute("count");
                        if (glwdcount == "0") {
                            alert("请您关联出差申请单后提交！"); return false;
                        }
                    }
                }
                //合同提交后控制
                if (dojo.byId("curflownum_forJS").value == "0") {
                    if (bxmc == "办公费" || bxmc == "办公用品费" || bxmc == "邮电费" || bxmc == "修理费" || bxmc == "其他费") {
                        var str4 = dojo.byId("fldhtxzid_xm").value;
                        if (str4 != "") {
                            //alert(dojo.byId("hfldData_qk").value)
                            var str5 = "";
                            var str6 = "";
                            var arry = str4.split(",");
                            for (j = 0; j < arry.length; j++) {
                                //alert(arry[j]);
                                if (str5 == "") {
                                    str5 = document.getElementById("ht_" + arry[j]).value
                                } else {
                                    str5 = str5 + ";" + document.getElementById("ht_" + arry[j]).value;
                                }
                                if (str6 == "") {
                                    str6 = document.getElementById("htyd_" + arry[j]).value
                                } else {
                                    str6 = str6 + ";" + document.getElementById("htyd_" + arry[j]).value;
                                }

                            }
                            document.getElementById("fldHtbqfkje").value = str5;
                            document.getElementById("fldHtydje").value = str6;
                            //alert(str5);
                            //--xz--add 0207
                            //如果是开口合同，所有单子的总报销金额 不可以超过各阶段总金额的 115%
                            if (document.getElementById("fldglhtlx").value == "开口合同") {
                                var zje = document.getElementById("fldglhtje").value;
                                zje = parseFloat(zje) * 1.15;
                                var fkje = parseFloat(document.getElementById("fldBaoxiaoje_xm").value).toFixed(2);
                                fkje = parseFloat(fkje) + parseFloat(document.getElementById("fldglhtfkje").value);
                                fkje = fkje.toFixed(2);
                                if (fkje > zje) {
                                    alert("合同付款金额应小于合同总金额的115%,请重新填写！"); return false;
                                }

                            } else {
                                //--xz--end 0207
                                if (str5.indexOf(";") == -1) {
                                    if (parseFloat(document.getElementById("fldBaoxiaoje_xm").value).toFixed(2) != parseFloat(str5).toFixed(2)) {
                                        alert("合同本期各阶段分摊金额应等于付款金额,请重新填写！"); return false;
                                    }
                                } else {
                                    var arr = str5.split(";");
                                    var strtotal;
                                    strtotal = 0;
                                    for (i = 0; i < arr.length; i++) {
                                        strtotal = parseFloat(strtotal) + parseFloat(arr[i]);
                                    }
                                    //alert(strtotal);
                                    if (parseFloat(document.getElementById("fldBaoxiaoje_xm").value).toFixed(2) != parseFloat(strtotal).toFixed(2)) {
                                        alert("合同本期各阶段分摊金额总计应等于付款金额,请重新填写！"); return false;
                                    }
                                }
                            }
                        }
                    }

                }

                //其他费用计算二级费用类型
                if (bxmc == "其他费") {
                    //dojo.byId("fldBxfeeXmbh_xm").value=dojo.byId("fldqtfylx_xm").value;
                    dojo.forEach(dojo.byId("fldqtfylx_xm").options,
                    function(e, i) {
                        if (e.selected == true) {
                            dojo.byId("fldqtfylxtext_xm").value = e.text;
                        }
                    })
                }
                var sfmjzj;
                if (dojo.byId("fldShifmjzj_xm")) {
                    dojo.forEach(dojo.query("input[name='fldShifmjzj_xm']"),
                    function(e, i) {
                        if (e.checked == true) {
                            sfmjzj = e.value;
                        }
                    })
                }

                //提交校验是否配置项目编号
                if (bxmc != "会议费" && sfmjzj != "是") {
                    var xmbh = dojo.byId("fldBxfeeXmbh_xm").value;
                    if (xmbh == "") {
                        alert("您好，没有配置报销对应的项目编号，请联系管理员"); return false;
                    }
                }
            }

            //获取是否关联预算
            var fydl;
            var xmmc;
            dojo.forEach(dojo.byId("fldfylx_xm").options,
            function(e, i) {
                if (e.selected) {
                    fydl = e.value;
                }
            });
            if (bxmc == "其他费") {
                xmmc = dojo.byId("fldqtfylxtext_xm").value;
            } else {
                xmmc = bxmc;
            }
            var ifglys = fnifglys(fydl, xmmc);
            dojo.byId("fldifglys_xm").value = ifglys;
            //提交判断是否超预算
            //是否是一季度的报销1-4月份
            var ifyjd = dojo.byId("fldisyjdbx_xm").value;
            //会议需要做会议费申请预算控制的需要把会议费单独拿出来
            if (bxmc == "会议费") {
                var ifcys = fnifcys(bxmc, fydl, xmmc);
                if (ifcys == "1") {
                    return false;
                }
            } else {
                //出去会议费的费用一季度的报销是不做预算控制的。
                if (ifyjd != "1" && sfmjzj != "是" && ifglys != "否") {
                    var ifcys = fnifcys(bxmc, fydl, xmmc);
                    if (ifcys == "1") {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}