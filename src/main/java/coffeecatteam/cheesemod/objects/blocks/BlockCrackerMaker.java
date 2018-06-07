package coffeecatteam.cheesemod.objects.blocks;

import java.util.List;
import java.util.Random;

import coffeecatteam.cheesemod.CheeseMod;
import coffeecatteam.cheesemod.init.InitBlock;
import coffeecatteam.cheesemod.objects.tileentity.TileEntityCrackerMaker;
import coffeecatteam.cheesemod.util.handlers.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrackerMaker extends BlockContainer {
	private final boolean isBurning;
	private static boolean keepInventory;

	boolean active;

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockCrackerMaker(String name, float hardness, float resistance, Material material, int harvestLevel,
			boolean active) {
		super(material);
		setUnlocalizedName(name);
		this.active = active;
		setRegistryName(!active ? name : name + "_active");
		setDefaultState(blockState.getBaseState());
		setHarvestLevel("pickaxe", harvestLevel);
		setCreativeTab(CheeseMod.CHEESETAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		blockSoundType = SoundType.STONE;
		blockParticleGravity = 1.0f;
		lightOpacity = 20;
		setTickRandomly(false);
		useNeighborBrightness = false;
		isBurning = active;
	}

	@Override
	public Block setLightLevel(float value) {
		return super.setLightLevel(!active ? 0.0f : 0.875f);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(InitBlock.CRACKER_MAKER_IDLE);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isBurning) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = (double) pos.getZ() + 0.5D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			double offset;

			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D,
						SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 2.0F, 2.0F, false);
			}

			offset = rand.nextDouble() * 0.2D / 0.5D;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.3D + offset, d1 + 0.2D, d2 - 0.3D + offset,
					0.0D, 0.0D, 0.0D, new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.3D + offset, d1 + 0.1D, d2 - 0.3D + offset, 0.0D,
					0.0D, 0.0D, new int[0]);

			offset = rand.nextDouble() * 0.2D / 0.5D;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.3D - offset, d1 + 0.2D, d2 - 0.3D + offset,
					0.0D, 0.0D, 0.0D, new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.3D - offset, d1 + 0.1D, d2 - 0.3D + offset, 0.0D,
					0.0D, 0.0D, new int[0]);

			offset = rand.nextDouble() * 0.2D / 0.5D;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.3D - offset, d1 + 0.2D, d2 + 0.3D - offset,
					0.0D, 0.0D, 0.0D, new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.3D - offset, d1 + 0.1D, d2 + 0.3D - offset, 0.0D,
					0.0D, 0.0D, new int[0]);

			offset = rand.nextDouble() * 0.2D / 0.5D;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.3D + offset, d1 + 0.2D, d2 + 0.3D - offset,
					0.0D, 0.0D, 0.0D, new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.3D + offset, d1 + 0.1D, d2 + 0.3D - offset, 0.0D,
					0.0D, 0.0D, new int[0]);
		}
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote)
			playerIn.openGui(CheeseMod.instance, GuiHandler.CRACKER_MAKER_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());

		return true;
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;

		if (active) {
			worldIn.setBlockState(pos, InitBlock.CRACKER_MAKER_ACTIVE.getDefaultState(), 3);
			worldIn.setBlockState(pos, InitBlock.CRACKER_MAKER_ACTIVE.getDefaultState(), 3);
		} else {
			worldIn.setBlockState(pos, InitBlock.CRACKER_MAKER_IDLE.getDefaultState(), 3);
			worldIn.setBlockState(pos, InitBlock.CRACKER_MAKER_IDLE.getDefaultState(), 3);
		}

		keepInventory = false;

		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCrackerMaker();
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state, 2);
		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof TileEntityCrackerMaker) {
				((TileEntityCrackerMaker) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!keepInventory) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof TileEntityCrackerMaker) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityCrackerMaker) tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}

	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(InitBlock.CRACKER_MAKER_IDLE);
	}

	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		// Add Text
		tooltip.add("CHEESE & CRACKERS!");
		super.addInformation(stack, player, tooltip, advanced);
	}

	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		this.setDefaultFacing(world, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	public int getRenderType() {
		return -1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}