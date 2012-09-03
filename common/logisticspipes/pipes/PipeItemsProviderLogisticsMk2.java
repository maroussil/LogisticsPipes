package logisticspipes.pipes;

import logisticspipes.config.Textures;
import logisticspipes.main.LogisticsRequest;

public class PipeItemsProviderLogisticsMk2 extends PipeItemsProviderLogistics {

	public PipeItemsProviderLogisticsMk2(int itemID) {
		super(itemID);
	}

	@Override
	public int getCenterTexture() {
		return Textures.LOGISTICSPIPE_PROVIDERMK2_TEXTURE;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (!_orderManager.hasOrders() || worldObj.getWorldTime() % 6 != 0) return;
		for(int i = 0; i < 64; i++) {
			if(_orderManager.hasOrders()) {
				LogisticsRequest order = _orderManager.getNextRequest();
				int sent = sendItem(order.getItem(), order.numberLeft(), order.getDestination().getRouter().getId());
				if (sent > 0){
					_orderManager.sendSuccessfull(sent);
				}
				else {
					_orderManager.sendFailed();
				}
			}
		}
	}

	@Override
	public ItemSendMode getItemSendMode() {
		return ItemSendMode.Fast;
	}
}
