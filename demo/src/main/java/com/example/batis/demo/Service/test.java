package com.example.batis.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;

@Component
public class test {

    @Autowired
    private int test;

    @PostConstruct
    public void init() {

        String x = "123";
        String test = "H";
        BigDecimal decimal = new BigDecimal(x);

        if (test.contentEquals("S")){
            System.out.printf("increase : "+decimal);

        }else{
            System.out.printf("decrease : "+decimal.negate());
        }
    }

    public void parse(String xmlResponse) {
        try {
            System.out.println("xmlResponse: " + xmlResponse);
            // Create a DocumentBuilder instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML string into a Document object
            Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));

            // Get the root element
            Element rootElement = document.getDocumentElement();

            // Get the GOODSMVT_HEADER element
            Element headerElement = (Element) rootElement.getElementsByTagName("GOODSMVT_HEADER").item(0);

            // Get all items within GOODSMVT_HEADER
            NodeList itemHeaderNodeList = headerElement.getElementsByTagName("item");
            if (itemHeaderNodeList.getLength() == 0) {
                Element returnElement = (Element) rootElement.getElementsByTagName("RETURN").item(0);
                NodeList messageNodeList = returnElement.getElementsByTagName("MESSAGE");

                if (messageNodeList.getLength() > 0) {
                    System.out.println("No data found for article in question");
                    Node messageNode = messageNodeList.item(0);
                    String message = messageNode.getTextContent();
                    Element materialRaElement = (Element) rootElement.getElementsByTagName("MATERIAL_RA").item(0);
                    Element materialItemElement = (Element) materialRaElement.getElementsByTagName("item").item(0);
                    String materialLow = materialItemElement.getElementsByTagName("LOW").item(0).getTextContent();

                    Element pstngDateRaElement = (Element) rootElement.getElementsByTagName("PSTNG_DATE_RA").item(0);
                    Element pstngDateItemElement = (Element) pstngDateRaElement.getElementsByTagName("item").item(0);
                    String pstngDateLow = pstngDateItemElement.getElementsByTagName("LOW").item(0).getTextContent();
                    String pstngDateHigh = pstngDateItemElement.getElementsByTagName("HIGH").item(0).getTextContent();

                    System.out.println(message);
                    System.out.println(materialLow);
                    System.out.println(pstngDateLow);
                    System.out.println(pstngDateHigh);


                    // linkJdbcTemplate.execute("INSERT INTO DRILLDOWN_HEADER () VALUES ()");
                } else {
                    // Handle the case when the "MESSAGE" element is not found
                }
            }
            // Iterate through the item elements
            System.out.printf("itemHeaderNodeList.getLength() : " + itemHeaderNodeList.getLength() + "\n");
            for (int i = 0; i < itemHeaderNodeList.getLength(); i++) {
                Element itemElement = (Element) itemHeaderNodeList.item(i);

                String matDoc = itemElement.getElementsByTagName("MAT_DOC").item(0).getTextContent();
                String docYear = itemElement.getElementsByTagName("DOC_YEAR").item(0).getTextContent();
                String trEvType = itemElement.getElementsByTagName("TR_EV_TYPE").item(0).getTextContent();
                String docDate = itemElement.getElementsByTagName("DOC_DATE").item(0).getTextContent();
                String pstngDate = itemElement.getElementsByTagName("PSTNG_DATE").item(0).getTextContent();
                String entryDate = itemElement.getElementsByTagName("ENTRY_DATE").item(0).getTextContent();
                String entryTime = itemElement.getElementsByTagName("ENTRY_TIME").item(0).getTextContent();
                String username = itemElement.getElementsByTagName("USERNAME").item(0).getTextContent();
                String verGrGiSlip = itemElement.getElementsByTagName("VER_GR_GI_SLIP").item(0).getTextContent();
                String expImpNo = itemElement.getElementsByTagName("EXPIMP_NO").item(0).getTextContent();
                String refDocNo = itemElement.getElementsByTagName("REF_DOC_NO").item(0).getTextContent();
                String headerTxt = itemElement.getElementsByTagName("HEADER_TXT").item(0).getTextContent();
                String refDocNoLong = itemElement.getElementsByTagName("REF_DOC_NO_LONG").item(0).getTextContent();

                String sql = "INSERT INTO DRILLDOWN_HEADER (MatDoc, DocYear, TrEvType, DocDate, PstngDate, EntryDate, EntryTime, Username, VerGrGiSlip, ExpimpNo, RefDocNo, HeaderTxt, RefDocNoLong, Datetime) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";

                // linkJdbcTemplate.update(sql, matDoc, docYear, trEvType, docDate, pstngDate, entryDate, entryTime, username, verGrGiSlip, expImpNo, refDocNo, headerTxt, refDocNoLong);

            }

            // Get the GOODSMVT_ITEMS element
            Element itemDetailHeader = (Element) rootElement.getElementsByTagName("GOODSMVT_ITEMS").item(0);

            // Get all the item elements
            NodeList itemDetailNodes = itemDetailHeader.getElementsByTagName("item");

            // Iterate through each item element
            for (int i = 0; i < itemDetailNodes.getLength(); i++) {
                Element itemElement = (Element) itemDetailNodes.item(i);

                String matDoc = itemElement.getElementsByTagName("MAT_DOC").item(0).getTextContent();
                String docYear = itemElement.getElementsByTagName("DOC_YEAR").item(0).getTextContent();
                String matdocItm = itemElement.getElementsByTagName("MATDOC_ITM").item(0).getTextContent();
                String material = itemElement.getElementsByTagName("MATERIAL").item(0).getTextContent();
                String plant = itemElement.getElementsByTagName("PLANT").item(0).getTextContent();
                String stgeLoc = itemElement.getElementsByTagName("STGE_LOC").item(0).getTextContent();
                String batch = itemElement.getElementsByTagName("BATCH").item(0).getTextContent();
                String moveType = itemElement.getElementsByTagName("MOVE_TYPE").item(0).getTextContent();
                String stckType = itemElement.getElementsByTagName("STCK_TYPE").item(0).getTextContent();
                String specStock = itemElement.getElementsByTagName("SPEC_STOCK").item(0).getTextContent();
                String vendor = itemElement.getElementsByTagName("VENDOR").item(0).getTextContent();
                String customer = itemElement.getElementsByTagName("CUSTOMER").item(0).getTextContent();
                String salesOrd = itemElement.getElementsByTagName("SALES_ORD").item(0).getTextContent();
                String sOrdItem = itemElement.getElementsByTagName("S_ORD_ITEM").item(0).getTextContent();
                String schedLine = itemElement.getElementsByTagName("SCHED_LINE").item(0).getTextContent();
                String valType = itemElement.getElementsByTagName("VAL_TYPE").item(0).getTextContent();
                String entryQnt = itemElement.getElementsByTagName("ENTRY_QNT").item(0).getTextContent();
                String entryUom = itemElement.getElementsByTagName("ENTRY_UOM").item(0).getTextContent();
                String entryUomIso = itemElement.getElementsByTagName("ENTRY_UOM_ISO").item(0).getTextContent();
                String poPrQnt = itemElement.getElementsByTagName("PO_PR_QNT").item(0).getTextContent();
                String orderprUn = itemElement.getElementsByTagName("ORDERPR_UN").item(0).getTextContent();
                String orderprUnIso = itemElement.getElementsByTagName("ORDERPR_UN_ISO").item(0).getTextContent();
                String poNumber = itemElement.getElementsByTagName("PO_NUMBER").item(0).getTextContent();
                String poItem = itemElement.getElementsByTagName("PO_ITEM").item(0).getTextContent();
                String shipping = itemElement.getElementsByTagName("SHIPPING").item(0).getTextContent();
                String compShip = itemElement.getElementsByTagName("COMP_SHIP").item(0).getTextContent();
                String noMoreGr = itemElement.getElementsByTagName("NO_MORE_GR").item(0).getTextContent();
                String itemText = itemElement.getElementsByTagName("ITEM_TEXT").item(0).getTextContent();
                String grRcpt = itemElement.getElementsByTagName("GR_RCPT").item(0).getTextContent();
                String unloadPt = itemElement.getElementsByTagName("UNLOAD_PT").item(0).getTextContent();
                String costcenter = itemElement.getElementsByTagName("COSTCENTER").item(0).getTextContent();
                String orderid = itemElement.getElementsByTagName("ORDERID").item(0).getTextContent();
                String orderItno = itemElement.getElementsByTagName("ORDER_ITNO").item(0).getTextContent();
                String calcMotive = itemElement.getElementsByTagName("CALC_MOTIVE").item(0).getTextContent();
                String assetNo = itemElement.getElementsByTagName("ASSET_NO").item(0).getTextContent();
                String subNumber = itemElement.getElementsByTagName("SUB_NUMBER").item(0).getTextContent();
                String reservNo = itemElement.getElementsByTagName("RESERV_NO").item(0).getTextContent();
                String resItem = itemElement.getElementsByTagName("RES_ITEM").item(0).getTextContent();
                String resType = itemElement.getElementsByTagName("RES_TYPE").item(0).getTextContent();
                String withdrawn = itemElement.getElementsByTagName("WITHDRAWN").item(0).getTextContent();
                String moveMat = itemElement.getElementsByTagName("MOVE_MAT").item(0).getTextContent();
                String movePlant = itemElement.getElementsByTagName("MOVE_PLANT").item(0).getTextContent();
                String moveStloc = itemElement.getElementsByTagName("MOVE_STLOC").item(0).getTextContent();
                String moveBatch = itemElement.getElementsByTagName("MOVE_BATCH").item(0).getTextContent();
                String moveValType = itemElement.getElementsByTagName("MOVE_VAL_TYPE").item(0).getTextContent();
                String mvtInd = itemElement.getElementsByTagName("MVT_IND").item(0).getTextContent();
                String moveReas = itemElement.getElementsByTagName("MOVE_REAS").item(0).getTextContent();
                String rlEstKey = itemElement.getElementsByTagName("RL_EST_KEY").item(0).getTextContent();
                String refDate = itemElement.getElementsByTagName("REF_DATE").item(0).getTextContent();
                String costObj = itemElement.getElementsByTagName("COST_OBJ").item(0).getTextContent();
                String profitSegmNo = itemElement.getElementsByTagName("PROFIT_SEGM_NO").item(0).getTextContent();
                String profitCtr = itemElement.getElementsByTagName("PROFIT_CTR").item(0).getTextContent();
                String wbsElem = itemElement.getElementsByTagName("WBS_ELEM").item(0).getTextContent();
                String network = itemElement.getElementsByTagName("NETWORK").item(0).getTextContent();
                String activity = itemElement.getElementsByTagName("ACTIVITY").item(0).getTextContent();
                String partAcct = itemElement.getElementsByTagName("PART_ACCT").item(0).getTextContent();
                String amountLc = itemElement.getElementsByTagName("AMOUNT_LC").item(0).getTextContent();
                String amountSv = itemElement.getElementsByTagName("AMOUNT_SV").item(0).getTextContent();
                String currency = itemElement.getElementsByTagName("CURRENCY").item(0).getTextContent();
                String currencyIso = itemElement.getElementsByTagName("CURRENCY_ISO").item(0).getTextContent();
                String refDocYr = itemElement.getElementsByTagName("REF_DOC_YR").item(0).getTextContent();
                String refDoc = itemElement.getElementsByTagName("REF_DOC").item(0).getTextContent();
                String refDocIt = itemElement.getElementsByTagName("REF_DOC_IT").item(0).getTextContent();
                String expirydate = itemElement.getElementsByTagName("EXPIRYDATE").item(0).getTextContent();
                String prodDate = itemElement.getElementsByTagName("PROD_DATE").item(0).getTextContent();
                String fund = itemElement.getElementsByTagName("FUND").item(0).getTextContent();
                String fundsCtr = itemElement.getElementsByTagName("FUNDS_CTR").item(0).getTextContent();
                String cmmtItem = itemElement.getElementsByTagName("CMMT_ITEM").item(0).getTextContent();
                String valSalesOrd = itemElement.getElementsByTagName("VAL_SALES_ORD").item(0).getTextContent();
                String valSOrdItem = itemElement.getElementsByTagName("VAL_S_ORD_ITEM").item(0).getTextContent();
                String valWbsElem = itemElement.getElementsByTagName("VAL_WBS_ELEM").item(0).getTextContent();
                String coBusproc = itemElement.getElementsByTagName("CO_BUSPROC").item(0).getTextContent();
                String actType = itemElement.getElementsByTagName("ACTTYPE").item(0).getTextContent();
                String supplVend = itemElement.getElementsByTagName("SUPPL_VEND").item(0).getTextContent();
                String xAutoCre = itemElement.getElementsByTagName("X_AUTO_CRE").item(0).getTextContent();
                String materialExternal = itemElement.getElementsByTagName("MATERIAL_EXTERNAL").item(0).getTextContent();
                String materialGuid = itemElement.getElementsByTagName("MATERIAL_GUID").item(0).getTextContent();
                String materialVersion = itemElement.getElementsByTagName("MATERIAL_VERSION").item(0).getTextContent();
                String moveMatExternal = itemElement.getElementsByTagName("MOVE_MAT_EXTERNAL").item(0).getTextContent();
                String moveMatGuid = itemElement.getElementsByTagName("MOVE_MAT_GUID").item(0).getTextContent();
                String moveMatVersion = itemElement.getElementsByTagName("MOVE_MAT_VERSION").item(0).getTextContent();
                String grantNbr = itemElement.getElementsByTagName("GRANT_NBR").item(0).getTextContent();
                String cmmtItemLong = itemElement.getElementsByTagName("CMMT_ITEM_LONG").item(0).getTextContent();
                String funcAreaLong = itemElement.getElementsByTagName("FUNC_AREA_LONG").item(0).getTextContent();
                String lineId = itemElement.getElementsByTagName("LINE_ID").item(0).getTextContent();
                String parentId = itemElement.getElementsByTagName("PARENT_ID").item(0).getTextContent();
                String lineDepth = itemElement.getElementsByTagName("LINE_DEPTH").item(0).getTextContent();
                String budgetPeriod = itemElement.getElementsByTagName("BUDGET_PERIOD").item(0).getTextContent();
                String earmarkedNumber = itemElement.getElementsByTagName("EARMARKED_NUMBER").item(0).getTextContent();
                String earmarkedItem = itemElement.getElementsByTagName("EARMARKED_ITEM").item(0).getTextContent();
                String stkSegment = itemElement.getElementsByTagName("STK_SEGMENT").item(0).getTextContent();

                String sql = "INSERT INTO DRILLDOWN_DETAIL ( MatDoc, DocYear, MatdocItm, Material, Plant, StgeLoc, Batch, MoveType, StckType, SpecStock, Vendor, Customer, SalesOrd, SOrdItem, SchedLine, ValType, EntryQnt, EntryUom, EntryUomIso, PoPrQnt, OrderprUn, OrderprUnIso, PoNumber, PoItem, Shipping, CompShip, NoMoreGr, ItemText, GrRcpt, UnloadPt, Costcenter, Orderid, OrderItno, CalcMotive, AssetNo, SubNumber, ReservNo, ResItem, ResType, Withdrawn, MoveMat, MovePlant, MoveStloc, MoveBatch, MoveValType, MvtInd, MoveReas, RlEstKey, RefDate, CostObj, ProfitSegmNo, ProfitCtr, WbsElem, Network, Activity, PartAcct, AmountLc, AmountSv, Currency, CurrencyIso, RefDocYr, RefDoc, RefDocIt, Expirydate, ProdDate, Fund, FundsCtr, CmmtItem, ValSalesOrd, ValSOrdItem, ValWbsElem, CoBusproc, ActType, SupplVend, XAutoCre, MaterialExternal, MaterialGuid, MaterialVersion, MoveMatExternal, MoveMatGuid, MoveMatVersion, GrantNbr, CmmtItemLong, FuncAreaLong, LineId, ParentId, LineDepth, BudgetPeriod, EarmarkedNumber, EarmarkedItem, StkSegment) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                //  linkJdbcTemplate.update(sql, matDoc, docYear, matdocItm, material, plant, stgeLoc, batch, moveType, stckType, specStock, vendor, customer, salesOrd, sOrdItem, schedLine, valType, entryQnt, entryUom, entryUomIso, poPrQnt, orderprUn, orderprUnIso, poNumber, poItem, shipping, compShip, noMoreGr, itemText, grRcpt, unloadPt, costcenter, orderid, orderItno, calcMotive, assetNo, subNumber, reservNo, resItem, resType, withdrawn, moveMat, movePlant, moveStloc, moveBatch, moveValType, mvtInd, moveReas, rlEstKey, refDate, costObj, profitSegmNo, profitCtr, wbsElem, network, activity, partAcct, amountLc, amountSv, currency, currencyIso, refDocYr, refDoc, refDocIt, expirydate, prodDate, fund, fundsCtr, cmmtItem, valSalesOrd, valSOrdItem, valWbsElem, coBusproc, actType, supplVend, xAutoCre, materialExternal, materialGuid, materialVersion, moveMatExternal, moveMatGuid, moveMatVersion, grantNbr, cmmtItemLong, funcAreaLong, lineId, parentId, lineDepth, budgetPeriod, earmarkedNumber, earmarkedItem, stkSegment);

            }
        } catch (Exception e) {
            e.printStackTrace();
            // jdbcTemplate.execute("INSERT INTO HQS_ERRORS (Details, Datetime) VALUES ('Error in hqAPI - parseBapiResponse : "+e.getMessage().replaceAll("'","")+"' , GETDATE())");
        }
    }

}
