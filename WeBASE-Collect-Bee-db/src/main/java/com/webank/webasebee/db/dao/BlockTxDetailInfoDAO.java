/**
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.webasebee.db.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.Transaction;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webank.webasebee.common.bo.data.BlockTxDetailInfoBO;
import com.webank.webasebee.db.entity.BlockTxDetailInfo;
import com.webank.webasebee.db.repository.BlockTxDetailInfoRepository;

import cn.hutool.core.bean.BeanUtil;

/**
 * BlockTxDetailInfoDAO
 *
 * @Description: Block transaction detail data access object
 * @author graysonzhang
 * @data 2018-12-20 15:01:27
 *
 */
@Service
public class BlockTxDetailInfoDAO {

    /** @Fields web3j : web3j */
    @Autowired
    private Web3j web3j;

    /** @Fields blockTxDetailInfoRepository : block transaction detail info repository */
    @Autowired
    private BlockTxDetailInfoRepository blockTxDetailInfoRepository;

    /**
     * Get block transaction detail info from transaction receipt object and insert BlockTxDetailInfo into db.
     * 
     * @param receipt : TransactionReceipt
     * @param blockTimeStamp
     * @param contractName: contract name
     * @param methodName: method name
     * @return void
     * @throws IOException
     */
    public void save(TransactionReceipt receipt, BigInteger blockTimeStamp, String contractName, String methodName)
            throws IOException {
        BlockTxDetailInfo blockTxDetailInfo = new BlockTxDetailInfo();
        blockTxDetailInfo.setBlockHash(receipt.getBlockHash());
        blockTxDetailInfo.setBlockHeight(receipt.getBlockNumber().longValue());
        blockTxDetailInfo.setContractName(contractName);

        blockTxDetailInfo.setMethodName(methodName.substring(contractName.length()));
        Transaction transaction =
                web3j.getTransactionByHash(receipt.getTransactionHash()).send().getTransaction().get();
        blockTxDetailInfo.setTxFrom(transaction.getFrom());
        blockTxDetailInfo.setTxTo(transaction.getTo());
        blockTxDetailInfo.setTxHash(receipt.getTransactionHash());
        blockTxDetailInfo.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
        blockTxDetailInfoRepository.save(blockTxDetailInfo);
    }

    public void save(BlockTxDetailInfoBO bo) {
        BlockTxDetailInfo blockTxDetailInfo = new BlockTxDetailInfo();
        BeanUtil.copyProperties(bo, blockTxDetailInfo, true);
        blockTxDetailInfoRepository.save(blockTxDetailInfo);
    }

    public void save(List<BlockTxDetailInfoBO> list) {
        list.forEach(this::save);
    }

}